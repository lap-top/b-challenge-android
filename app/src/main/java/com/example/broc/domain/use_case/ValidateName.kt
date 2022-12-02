package com.example.broc.domain.use_case

import com.example.broc.common.Constants
import javax.inject.Inject

class ValidateName @Inject constructor() {
    operator fun invoke(name: String): ValidationResponse {
        if (name.length < Constants.NAME_MIN_LENGTH) { // Length check
            return ValidationResponse(
                success = false,
                errorMessage = Constants.VALIDATION_NAME_LENGTH
            )
        }
        if (!name.trim().all { it.isLetter() }) { // Only Character check
            return ValidationResponse(
                success = false,
                errorMessage = Constants.VALIDATION_NAME_FORMAT
            )
        }
        return ValidationResponse(success = true)
    }
}