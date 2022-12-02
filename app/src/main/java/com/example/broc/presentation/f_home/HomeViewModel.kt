package com.example.broc.presentation.f_home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.broc.common.Resource
import com.example.broc.domain.use_case.GetEmailSent
import com.example.broc.domain.use_case.StoreEmailSent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getEmailSent: GetEmailSent,
    private val storeEmailSent: StoreEmailSent
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())

    // The UI collects from this StateFlow to get its state updates
    val state: StateFlow<HomeState> = _state

    private val responseEventChannel: Channel<ResponseEvent> = Channel()
    val responseEvents: Flow<ResponseEvent> = responseEventChannel.receiveAsFlow()

    fun cancelInvite() {
        storeEmailSent("").onCompletion {
            _state.value = HomeState(email = "")
            responseEventChannel.send(ResponseEvent.Success)
        }.launchIn(viewModelScope)
    }

    fun getEmail() {
        getEmailSent().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    if (result.data != "") {
                        _state.value = HomeState(email = result.data)
                    } else {
                        _state.value = HomeState()
                    }
                }
                is Resource.Error -> {
                    _state.value = HomeState()
                }
                is Resource.Loading -> {}
            }
        }.launchIn(viewModelScope)
    }

    sealed class ResponseEvent {
        object Success : ResponseEvent()
    }

}