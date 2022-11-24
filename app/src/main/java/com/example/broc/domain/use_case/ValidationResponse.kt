package com.example.broc.domain.use_case

data class ValidationResponse(
    val success: Boolean,
    val errorMessage: String? = null
)