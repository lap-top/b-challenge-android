package com.example.broc.presentation.home_dialog

sealed class HomeDialogEvent {
    data class NameChanged(val name: String) : HomeDialogEvent()
    data class EmailChanged(val email: String) : HomeDialogEvent()
    data class ConfirmEmailChanged(val confirmEmail: String) : HomeDialogEvent()
    object Submit : HomeDialogEvent()
}