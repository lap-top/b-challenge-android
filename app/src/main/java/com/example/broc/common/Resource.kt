package com.example.broc.common

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val code: Int = -1, val message: String) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}