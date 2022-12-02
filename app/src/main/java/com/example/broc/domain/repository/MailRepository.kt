package com.example.broc.domain.repository

import com.example.broc.common.Resource

// Separating the interface from implementation allows ease of testing with mock
interface MailRepository {
    suspend fun postMail(name: String, email: String): Resource<Boolean>
}