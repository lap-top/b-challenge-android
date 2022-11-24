package com.example.broc.presentation.home_dialog

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.broc.common.Resource
import com.example.broc.domain.use_case.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject


// Maps use cases to compose
@HiltViewModel
class HomeDialogViewModel @Inject constructor(
    private val validateName: ValidateName = ValidateName(),
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validateRepeatedEmail: ValidateRepeatedEmail = ValidateRepeatedEmail(),
    private val postInviteUseCase: PostInviteUseCase,
    private val storeEmailSent: StoreEmailSent
) : ViewModel() {
    // private mutable state so state cannot be tampered with from view
    private val _state = mutableStateOf(HomeDialogState())
    val state: State<HomeDialogState> = _state
    private val validationChannel: Channel<ValidationEvent> = Channel()
    val validationEvents: Flow<ValidationEvent> = validationChannel.receiveAsFlow()

    // Events called from View
    fun onEvent(event: HomeDialogEvent) {
        _state.value = state.value.copy(responseError = null)
        when (event) {

            is HomeDialogEvent.NameChanged -> {
                _state.value = state.value.copy(name = event.name)
            }
            is HomeDialogEvent.EmailChanged -> {
                _state.value = state.value.copy(email = event.email)
            }
            is HomeDialogEvent.ConfirmEmailChanged -> {
                _state.value = state.value.copy(confirmEmail = event.confirmEmail)
            }
            is HomeDialogEvent.Submit -> {
                if (!validateAll()) {
                    postInvite(_state.value.name, _state.value.email)
                }
            }
        }
    }
    // When validation passes, we call api (use case) in coroutine
    private fun postInvite(name: String, email: String) {
        postInviteUseCase(name, email).onEach { result ->
            when (result) {
                is Resource.Success<*> -> { // Save into datastore
                    storeEmailSent(true)
                    _state.value = state.value.copy(isLoading = false)
                    validationChannel.send(ValidationEvent.Success)
                }
                is Resource.Error<*> -> {
                    _state.value =
                        state.value.copy(responseError = result.message, isLoading = false)
                }
                is Resource.Loading<*> -> {
                    _state.value = state.value.copy(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }

    // validates all fields by calling their respected use case for validation
    private fun validateAll(): Boolean {
        val emailResult = validateEmail.invoke(_state.value.email)
        val confirmEmailResult =
            validateRepeatedEmail.invoke(_state.value.email, _state.value.confirmEmail)
        val nameResult = validateName.invoke(_state.value.name)

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

    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }


}