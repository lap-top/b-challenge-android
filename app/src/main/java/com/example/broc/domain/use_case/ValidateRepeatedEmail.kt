package com.example.broc.domain.use_case

import com.example.broc.common.Constants
import javax.inject.Inject

class ValidateRepeatedEmail @Inject constructor() {
    operator fun invoke(email: String, confirmEmail: String): ValidationResponse {
        if (email != confirmEmail) {
            return ValidationResponse(
                success = false,
                errorMessage = Constants.VALIDATION_EMAIL_MATCH
            )
        }
        return ValidationResponse(success = true)
    }
}
