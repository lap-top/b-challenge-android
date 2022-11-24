package com.example.broc.domain.use_case

import android.util.Patterns
import javax.inject.Inject

class ValidateEmail @Inject constructor() {
    operator fun invoke(email: String): ValidationResponse {
        if (email.isBlank()) {
            return ValidationResponse(
                success = false,
                "Email cannot be blank"
            ) // use string resources
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResponse(success = false, errorMessage = "Email not valid")
        }
        return ValidationResponse(success = true)
    }
}