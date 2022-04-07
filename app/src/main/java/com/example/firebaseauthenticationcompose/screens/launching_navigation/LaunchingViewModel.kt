package com.example.firebaseauthenticationcompose.screens.launching_navigation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.firebaseauthenticationcompose.Navigation.Screens
import com.example.firebaseauthenticationcompose.util.LoadingState
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LaunchingViewModel: ViewModel() {

    val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val _loginState = mutableStateOf(false)

    fun signInUserUsingGoogle(credential: AuthCredential, navController: NavController) = viewModelScope.launch {
        try {
            loadingState.emit(LoadingState.LOADING)
            Firebase.auth.signInWithCredential(credential).addOnCompleteListener {
                if (it.isSuccessful){
                    _loginState.value = true
                    navController.navigate(Screens.HomeScreen.route){
                        popUpTo(Screens.LaunchingNavigation.route){
                            inclusive = false
                        }
                    }
                }else {
                    _loginState.value = false
                }
            }
            if (_loginState.value) loadingState.emit(LoadingState.SUCCESS)

        } catch (e: Exception) {
            loadingState.emit(LoadingState.error(e.stackTraceToString()))
        }
    }
}
