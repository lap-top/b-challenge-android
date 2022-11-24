package com.example.broc.presentation.home_invite_sent

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.broc.domain.use_case.StoreEmailSent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeInviteSentViewModel @Inject constructor(private val storeEmailSent: StoreEmailSent) :
    ViewModel() {
    private val _dialogOpen = mutableStateOf(false)
    val dialogOpen: State<Boolean> = _dialogOpen
    fun toggleDialog() {
        _dialogOpen.value = !dialogOpen.value
    }

    fun setEmailSentFalse() {
        storeEmailSent(false)
    }
}