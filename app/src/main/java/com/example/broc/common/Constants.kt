package com.example.broc.common

object Constants {
    const val BASE_URL = "https://us-central1-blinkapp-684c1.cloudfunctions.net/"
    const val DATASTORE_NAME = "broccoli"
    const val EMAIL_HAS_SENT = "EMAIL_HAS_SENT"
    const val NAME_MIN_LENGTH = 3

    const val ERROR_GENERIC = "Unexpected error occurred, try restarting the app."
    const val ERROR_NETWORK = "Please check your internet connection."
    const val VALIDATION_EMAIL_FORMAT = "Email must be valid format."
    const val VALIDATION_EMAIL_BLANK = "Email must not be blank."
    const val VALIDATION_EMAIL_MATCH = "Emails must match."
    const val VALIDATION_NAME_LENGTH = "Name must be at least 3 characters long."
    const val VALIDATION_NAME_FORMAT = "Name must contain only letters and spacing."
}