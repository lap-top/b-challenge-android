package com.example.broc.domain.use_case

import com.example.broc.common.Constants
import com.example.broc.common.Resource
import com.example.broc.domain.model.EmailInvite
import com.example.broc.domain.repository.DataStoreRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEmailsActive @Inject constructor(private val dataStoreRepo: DataStoreRepo) {
    operator fun invoke(): Flow<Resource<List<EmailInvite>>> = flow {
        emit(Resource.Loading)
        emit(dataStoreRepo.getActiveInvites())
    }
}