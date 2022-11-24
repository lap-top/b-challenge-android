package com.example.broc.presentation.home

data class HomeState(
    val isLoading: Boolean = false,
    val emailValid: Boolean = false,
    val error: String = ""
)
