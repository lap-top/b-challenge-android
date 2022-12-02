package com.example.broc.domain.repository

import com.example.broc.common.Resource

interface DataStoreRepo {
    suspend fun putString(key: String, value: String)
    suspend fun getString(key: String): Resource<String>
}