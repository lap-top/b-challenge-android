package com.example.broc.data.repository

import com.example.broc.data.remote.BroccoliApi
import com.example.broc.domain.model.InvitePost
import com.example.broc.domain.repository.MailRepository
import javax.inject.Inject

class MailRepositoryImpl @Inject constructor(private val api: BroccoliApi) : MailRepository {
    override suspend fun postMail(name: String, email: String): Boolean {
        return api.postInvite(InvitePost(email = email, name = name))
    }
}