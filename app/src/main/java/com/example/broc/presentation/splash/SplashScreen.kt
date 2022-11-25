package com.example.broc.presentation.splash

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.broc.presentation.navigation.Screen

@Composable
fun SplashScreen(navController: NavController, viewModel: SplashViewModel = hiltViewModel()) {
    if (viewModel.getEmailSent() == true) { // Checks datastore through viewmodel
        navController.navigate(Screen.HomeScreenInviteSent.route)
    } else {
        navController.navigate(Screen.HomeScreen.route)
    }
}