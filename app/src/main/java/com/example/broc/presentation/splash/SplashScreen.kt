package com.example.broc.presentation.splash

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.broc.presentation.navigation.Screen

@Composable
fun SplashScreen(navController: NavController, dataStoreVM: DataStoreViewModel = hiltViewModel()) {
    if (dataStoreVM.getEmailSent() == true) {
        navController.navigate(Screen.HomeScreenInviteSent.route) // remove me for debugging purposesw
    } else {
        navController.navigate(Screen.HomeScreen.route)
    }
}