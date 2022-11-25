package com.example.broc.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.broc.presentation.home_dialog.HomeDialogViewModel
import com.example.broc.presentation.navigation.Screen

@Composable
fun SplashScreen(navController: NavController, viewModel: SplashViewModel = hiltViewModel()) {

    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.storeEvents.collect { event ->
            when (event) {
                is SplashViewModel.StoreEvent.EmailSent -> {
                    navController.navigate(Screen.HomeScreenInviteSent.route)
                }
                is SplashViewModel.StoreEvent.NoEmailSent -> {
                    navController.navigate(Screen.HomeScreen.route)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)

    )
}