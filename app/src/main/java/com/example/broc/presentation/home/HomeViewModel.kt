package com.example.broc.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.broc.domain.use_case.PostInviteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val postInviteUseCase: PostInviteUseCase) :
    ViewModel() {
    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

}