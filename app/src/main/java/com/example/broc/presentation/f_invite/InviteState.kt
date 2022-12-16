package com.example.broc.presentation.f_invite

import androidx.databinding.ObservableField

data class InviteState(
    var name: ObservableField<String> = ObservableField(""),
    val nameError: String? = null,
    val email: ObservableField<String> = ObservableField(""),
    val emailError: String? = null,
    val confirmEmail: ObservableField<String> = ObservableField(""),
    val confirmEmailError: String? = null,
    val responseError: String? = null,
    val isLoading: Boolean = false,
)
