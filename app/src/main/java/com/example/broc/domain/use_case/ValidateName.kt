package com.example.broc.domain.use_case

import javax.inject.Inject

class ValidateName @Inject constructor() {
    operator fun invoke(name: String): ValidationResponse {
        if (name.length <= 3) { // Length check
            return ValidationResponse(success = false, errorMessage = "Please specify a name")
        }
        if (!name.trim().all { it.isLetter() }) { // Only Character check
            return ValidationResponse(
                success = false,
                errorMessage = "Name must contain only letters and spacing."
            )
        }
        return ValidationResponse(success = true)
    }
}