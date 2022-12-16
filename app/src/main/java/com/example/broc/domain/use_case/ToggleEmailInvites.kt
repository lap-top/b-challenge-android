package com.example.broc.domain.use_case

import com.example.broc.domain.model.EmailInvite
import com.example.broc.domain.repository.DataStoreRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ToggleEmailInvites @Inject constructor(private val repository: DataStoreRepo)  {
    operator fun invoke(invites: List<EmailInvite>): Flow<Nothing> = flow {
        repository.toggleInvites(invites)
    }
}