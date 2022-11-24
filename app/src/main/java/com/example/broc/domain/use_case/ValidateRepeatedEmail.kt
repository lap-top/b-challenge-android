package com.example.broc.domain.use_case

import javax.inject.Inject

class ValidateRepeatedEmail @Inject constructor() {
    operator fun invoke(email: String, confirmEmail: String): ValidationResponse {
        if (email != confirmEmail) {
            return ValidationResponse(success = false, errorMessage = "The emails don't match")
        }
        return ValidationResponse(success = true)
    }
}
