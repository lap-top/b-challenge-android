package com.example.broc.domain.use_case

import com.example.broc.common.Constants
import com.example.broc.domain.repository.DataStoreRepo
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class GetEmailSent @Inject constructor(private val repository: DataStoreRepo) {
    fun invoke(): Boolean? = runBlocking {
        repository.getBoolean(Constants.EMAIL_HAS_SENT)
    }
}