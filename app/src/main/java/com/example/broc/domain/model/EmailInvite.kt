package com.example.broc.domain.model

import android.util.Log
import kotlinx.serialization.Serializable

@Serializable
data class EmailInvite(
    val email: String,
    var active: Boolean,
)