package com.example.broc.domain.use_case

import com.example.broc.common.Constants
import com.example.broc.common.Resource
import com.example.broc.domain.repository.DataStoreRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEmailSent @Inject constructor(private val repository: DataStoreRepo) {
    operator fun invoke(): Flow<Resource<String>> = flow {
        emit(Resource.Loading)
        emit(repository.getString(Constants.EMAIL_HAS_SENT))
    }
}