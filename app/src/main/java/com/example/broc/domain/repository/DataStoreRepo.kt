package com.example.broc.domain.repository

interface DataStoreRepo {
    suspend fun putBoolean(key: String, value: Boolean)
    suspend fun getBoolean(key: String): Boolean?
}