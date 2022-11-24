package com.example.broc.presentation.splash

import androidx.lifecycle.ViewModel
import com.example.broc.common.Constants.EMAIL_HAS_SENT
import com.example.broc.domain.repository.DataStoreRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

// checks if user has sent email by checking datastore
@HiltViewModel
class DataStoreViewModel @Inject constructor(private val datastoreRepository: DataStoreRepo) :
    ViewModel() {

    fun storeEmailSent(value: Boolean) = runBlocking {
        datastoreRepository.putBoolean(EMAIL_HAS_SENT, value)
    }

    fun getEmailSent(): Boolean? = runBlocking {
        datastoreRepository.getBoolean(EMAIL_HAS_SENT)
    }
}