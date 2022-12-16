package com.example.broc.domain.repository

import com.example.broc.common.Resource
import com.example.broc.domain.model.EmailInvite

interface DataStoreRepo {
    suspend fun toggleInvite(key: String, value:String)
    suspend fun putString(key: String, value: String)
    suspend fun getActiveInvites(): Resource<List<EmailInvite>>
    suspend fun toggleInvites(emails: List<EmailInvite>)
}