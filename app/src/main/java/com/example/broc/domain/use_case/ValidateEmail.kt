package com.example.broc.domain.use_case

import android.util.Patterns
import com.example.broc.common.Constants
import javax.inject.Inject


class ValidateEmail @Inject constructor() {
    operator fun invoke(email: String): ValidationResponse {
        if (email.isBlank()) {
            return ValidationResponse(
                success = false,
                Constants.VALIDATION_EMAIL_BLANK
            ) // use string resources
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResponse(
                success = false,
                errorMessage = Constants.VALIDATION_EMAIL_FORMAT
            )
        }
        return ValidationResponse(success = true)
    }
}