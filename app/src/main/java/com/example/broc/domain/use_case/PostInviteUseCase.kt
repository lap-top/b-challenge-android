package com.example.broc.domain.use_case

import com.example.broc.common.Resource
import com.example.broc.domain.repository.MailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

// Use case for post invite with hilt @Inject
class PostInviteUseCase @Inject constructor(private val repository: MailRepository) {
    operator fun invoke(name: String, email: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        emit(repository.postMail(name, email))
    }
}