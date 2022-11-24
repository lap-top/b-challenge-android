package com.example.broc.data.remote

import com.example.broc.domain.model.InvitePost
import retrofit2.http.Body
import retrofit2.http.POST

interface BroccoliApi {
    // No DTO as the response is 'Registered' otherwise it's the POST errorMessage body
    @POST("fakeAuth")
    suspend fun postInvite(@Body body: InvitePost): Boolean
}