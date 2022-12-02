package com.example.broc.domain.use_case

import com.example.broc.R
import javax.inject.Inject

class ValidateRepeatedEmail @Inject constructor() {
    operator fun invoke(email: String, confirmEmail: String): ValidationResponse {
        if (email != confirmEmail) {
            return ValidationResponse(success = false, errorMessage = R.string.validation_email_match.toString())
        }
        return ValidationResponse(success = true)
    }
}
