package com.example.broc.domain.use_case

import com.example.broc.common.Constants
import com.example.broc.common.Resource
import com.example.broc.domain.repository.DataStoreRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


class StoreEmailSent @Inject constructor(private val repository: DataStoreRepo) {
    operator fun invoke(value: Boolean): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.putBoolean(Constants.EMAIL_HAS_SENT, value)
            emit(Resource.Success(true))
        } catch(e: Exception) {
            emit(Resource.Error("No contents found in datastore"))
        }

    }

}