package com.example.broc.presentation.navigation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash")
    object HomeScreen : Screen("home")
    object InviteDialog : Screen("invite")
    object CongratsScreen : Screen("congrats")
    object HomeScreenInviteSent : Screen("homesent")
}