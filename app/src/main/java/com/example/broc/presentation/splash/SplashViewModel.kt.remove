package com.example.broc.presentation.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.broc.common.Constants.EMAIL_HAS_SENT
import com.example.broc.common.Resource
import com.example.broc.domain.repository.DataStoreRepo
import com.example.broc.domain.use_case.GetEmailSent
import com.example.broc.presentation.home_dialog.HomeDialogViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

// checks if user has sent email by checking datastore
@HiltViewModel
class SplashViewModel @Inject constructor(private val getEmailSent: GetEmailSent) : ViewModel() {

    // Event after fetching data source is done.
    private val storeEventChannel: Channel<StoreEvent> = Channel()
    val storeEvents: Flow<StoreEvent> = storeEventChannel.receiveAsFlow()

    sealed class StoreEvent {
        object EmailSent : StoreEvent()
        object NoEmailSent : StoreEvent()
    }

    init {
        getEmail()
    }

    private fun getEmail() {
        getEmailSent().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    if (result.data!!) {
                        storeEventChannel.send(StoreEvent.EmailSent)
                    } else {
                        storeEventChannel.send(StoreEvent.NoEmailSent)
                    }
                }
                is Resource.Error -> {
                    storeEventChannel.send(StoreEvent.NoEmailSent)
                }
                is Resource.Loading -> {
                    // do nothing
                }
            }
        }.launchIn(viewModelScope)
    }

}