package com.example.firebaseauthenticationcompose.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebaseauthenticationcompose.screens.home_screen.HomeScreen
import com.example.firebaseauthenticationcompose.screens.launching_navigation.LaunchingNavigation
import com.example.firebaseauthenticationcompose.screens.login_screen.LoginScreen
import com.example.firebaseauthenticationcompose.screens.signup_screen.SignUpScreen
import com.google.firebase.auth.FirebaseUser

@Composable
fun Navigation(user: FirebaseUser?) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = if(user == null)
        Screens.LaunchingNavigation.route else Screens.HomeScreen.route){
        composable( route = Screens.LaunchingNavigation.route ){
            LaunchingNavigation(navController)
        }
        composable( route = Screens.HomeScreen.route ) {
            HomeScreen(navController)
        }
        composable( route = Screens.LoginScreen.route ){
            LoginScreen(navController = navController)
        }
        composable( route = Screens.SignUpScreen.route ){
            SignUpScreen(navController = navController)
        }
    }
}