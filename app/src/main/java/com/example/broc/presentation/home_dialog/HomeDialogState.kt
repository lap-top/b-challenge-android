package com.example.broc.presentation.home_dialog

data class HomeDialogState(
    val name: String = "",
    val nameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val confirmEmail: String = "",
    val confirmEmailError: String? = null,
    val responseError: String? = null,
    val isLoading: Boolean = false,
)
