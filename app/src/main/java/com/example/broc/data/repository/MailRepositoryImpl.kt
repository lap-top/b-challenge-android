package com.example.broc.data.repository

import com.example.broc.common.Constants
import com.example.broc.common.Resource
import com.example.broc.data.remote.BroccoliApi
import com.example.broc.data.remote.ErrorResponse
import com.example.broc.domain.model.InvitePost
import com.example.broc.domain.repository.MailRepository
import com.haroldadmin.cnradapter.NetworkResponse
import javax.inject.Inject

class MailRepositoryImpl @Inject constructor(
    private val api: BroccoliApi,
) : MailRepository {
    override suspend fun postMail(name: String, email: String): Resource<Boolean> {
        return when (val post = api.postInvite(InvitePost(name, email))) {
            is NetworkResponse.Success<*, *> -> {
                Resource.Success(true)
            }
            is NetworkResponse.NetworkError<*, *> -> {
                Resource.Error(message = Constants.ERROR_NETWORK)
            }
            is NetworkResponse.ServerError<*, ErrorResponse> -> {
                Resource.Error(message = post.body?.errorMessage!!)
            }
            else -> {
                Resource.Error(message = Constants.ERROR_GENERIC)
            }
        }
    }
}