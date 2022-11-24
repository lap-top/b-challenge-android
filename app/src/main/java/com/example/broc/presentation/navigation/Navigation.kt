package com.example.broc.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.broc.presentation.cancel.GoodbyeScreen
import com.example.broc.presentation.congrats.CongratsScreen
import com.example.broc.presentation.home.HomeScreen
import com.example.broc.presentation.home_dialog.HomeDialogScreen
import com.example.broc.presentation.home_invite_sent.HomeScreenInviteSent
import com.example.broc.presentation.navigation.Screen
import com.example.broc.presentation.splash.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.InviteDialog.route) {
            HomeDialogScreen(navController = navController)
        }
        composable(route = Screen.CongratsScreen.route) {
            CongratsScreen(navController = navController)
        }
        composable(route = Screen.HomeScreenInviteSent.route) {
            HomeScreenInviteSent(navController = navController)
        }
        composable(route = Screen.GoodbyeScreen.route) {
            GoodbyeScreen(navController = navController)
        }
    }
}