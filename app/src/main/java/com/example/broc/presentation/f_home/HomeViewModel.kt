package com.example.broc.presentation.f_home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.broc.common.Resource
import com.example.broc.domain.model.EmailInvite
import com.example.broc.domain.use_case.GetEmailsActive
import com.example.broc.domain.use_case.ToggleEmailInvites
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getEmailsActive: GetEmailsActive,
    private val toggleEmailInvites: ToggleEmailInvites
) : ViewModel() {

    val emails: MutableStateFlow<List<EmailInvite>> = MutableStateFlow(emptyList())


    fun cancelInvites() {
        toggleEmailInvites(emails.value).onCompletion{
            emails.value = emails.value.filter{ item -> item.active }
        }.launchIn(viewModelScope)
    }

    fun getEmail() {
        getEmailsActive().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    if (result.data.isNotEmpty()) {
                        emails.value = result.data
                    }
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    sealed class ResponseEvent {
        object Success : ResponseEvent()
    }

}