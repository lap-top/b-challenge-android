package com.example.broc.domain.use_case

import com.example.broc.R
import javax.inject.Inject

class ValidateName @Inject constructor() {
    operator fun invoke(name: String): ValidationResponse {
        if (name.length < 3) { // Length check
            return ValidationResponse(
                success = false,
                errorMessage = R.string.validation_name_length.toString()
            )
        }
        if (!name.trim().all { it.isLetter() }) { // Only Character check
            return ValidationResponse(
                success = false,
                errorMessage = R.string.validation_name_format.toString()
            )
        }
        return ValidationResponse(success = true)
    }
}