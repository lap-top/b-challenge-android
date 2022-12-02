package com.example.broc.domain.use_case

import android.util.Patterns
import com.example.broc.R
import javax.inject.Inject


class ValidateEmail @Inject constructor() {
    operator fun invoke(email: String): ValidationResponse {
        if (email.isBlank()) {
            return ValidationResponse(
                success = false,
                R.string.validation_email_blank.toString()
            ) // use string resources
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResponse(
                success = false,
                errorMessage = R.string.validation_email_format.toString()
            )
        }
        return ValidationResponse(success = true)
    }
}