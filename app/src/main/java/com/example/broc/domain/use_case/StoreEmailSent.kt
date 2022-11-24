package com.example.broc.domain.use_case

import com.example.broc.common.Constants
import com.example.broc.domain.repository.DataStoreRepo
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class StoreEmailSent @Inject constructor(private val repository: DataStoreRepo) {
    operator fun invoke(value: Boolean) = runBlocking {
        repository.putBoolean(Constants.EMAIL_HAS_SENT, value)
    }
}