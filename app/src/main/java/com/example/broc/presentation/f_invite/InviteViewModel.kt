package com.example.broc.presentation.f_invite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.broc.common.Resource
import com.example.broc.domain.use_case.*
import com.example.broc.presentation.home_dialog.InviteEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

// Maps use cases to compose
@HiltViewModel
class InviteViewModel @Inject constructor(
    private val validateName: ValidateName = ValidateName(),
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validateRepeatedEmail: ValidateRepeatedEmail = ValidateRepeatedEmail(),
    private val postInviteUseCase: PostInviteUseCase,
    private val storeEmailSent: StoreEmailSent
) : ViewModel() {
    // private mutable state so state cannot be tampered with from view
    private val _state = MutableStateFlow(InviteState())

    val state: StateFlow<InviteState> = _state
    private val responseEventChannel: Channel<ResponseEvent> = Channel()
    val responseEvents: Flow<ResponseEvent> = responseEventChannel.receiveAsFlow()

    // Events called from View
    fun onEvent(event: InviteEvent) {
        _state.value = state.value.copy(responseError = null)
        when (event) {
            is InviteEvent.Submit -> {
                if (!validateAll()) {
                    postInvite(_state.value.name.get()!!, _state.value.email.get()!!)
                }
            }
        }
    }

    // When validation passes, we call api (use case) in coroutine
    private fun postInvite(name: String, email: String) {
        postInviteUseCase(name, email).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.wtf("testing2", "testie")
                    storeEmailSent(email).onCompletion {
                        _state.value = InviteState() // Reset State
                        responseEventChannel.send(ResponseEvent.Success)
                    }.launchIn(viewModelScope)
                }
                is Resource.Error -> {
                    Log.wtf("testing3", "testie")
                    _state.value =
                        state.value.copy(responseError = result.message, isLoading = false)
                }
                is Resource.Loading -> {
                    Log.wtf("testing4", "testie")
                    _state.value = state.value.copy(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }

    // validates all fields by calling their respected use case for validation
    private fun validateAll(): Boolean {
        val emailResult = validateEmail.invoke(_state.value.email.get()!!)
        val confirmEmailResult = validateRepeatedEmail.invoke(
            _state.value.email.get()!!,
            _state.value.confirmEmail.get()!!
        )
        val nameResult = validateName.invoke(_state.value.name.get()!!)

        val hasError = listOf(
            emailResult,
            confirmEmailResult,
            nameResult,
        ).any { !it.success }

        _state.value = state.value.copy(
            emailError = emailResult.errorMessage,
            confirmEmailError = confirmEmailResult.errorMessage,
            nameError = nameResult.errorMessage,
        )
        return hasError
    }

    sealed class ResponseEvent {
        object Success : ResponseEvent()
    }

}