package com.example.firebaseauthenticationcompose.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

sealed class Screens(val route:  String){
    object LaunchingNavigation: Screens("launching_navigation")
    object LoginScreen: Screens("main_screen")
    object SignUpScreen: Screens("signup_screen")
    object HomeScreen: Screens("home_screen")
}