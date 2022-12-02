package com.example.broc.data.remote

import com.example.broc.domain.model.InvitePost
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.Body
import retrofit2.http.POST

data class ErrorResponse(val errorMessage: String)
interface BroccoliApi {

    @POST("fakeAuth")
    suspend fun postInvite(@Body body: InvitePost): NetworkResponse<Boolean, ErrorResponse>
}