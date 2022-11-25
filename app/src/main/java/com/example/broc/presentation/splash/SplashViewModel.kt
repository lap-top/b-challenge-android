package com.example.broc.presentation.splash

import androidx.lifecycle.ViewModel
import com.example.broc.common.Constants.EMAIL_HAS_SENT
import com.example.broc.domain.repository.DataStoreRepo
import com.example.broc.domain.use_case.GetEmailSent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

// checks if user has sent email by checking datastore
@HiltViewModel
class SplashViewModel @Inject constructor(private val getEmailSent: GetEmailSent) :
    ViewModel() {

    fun getEmailSent(): Boolean? = runBlocking {
        getEmailSent()
    }
}