package com.example.broc.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(): ViewModel() {
    private val _state = MutableStateFlow(MainState.Success(""))
    // The UI collects from this StateFlow to get its state updates
    val state: StateFlow<MainState> = _state

    init {
        viewModelScope.launch {
            delay(1000)
            _state.value = MainState.Success("BANANAA")
            delay(10000)
            _state.value = MainState.Success("TESTING")
        }
    }
    sealed class MainState {
        data class Success(val data: String): MainState()
        data class Error(val exception: Throwable): MainState()
    }
}