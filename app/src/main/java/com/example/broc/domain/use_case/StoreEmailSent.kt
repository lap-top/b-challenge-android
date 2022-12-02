package com.example.broc.domain.use_case

import com.example.broc.common.Constants
import com.example.broc.common.Resource
import com.example.broc.domain.repository.DataStoreRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class StoreEmailSent @Inject constructor(private val repository: DataStoreRepo) {
    operator fun invoke(value: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        repository.putString(Constants.EMAIL_HAS_SENT, value)
        emit(Resource.Success(true))
    }
}