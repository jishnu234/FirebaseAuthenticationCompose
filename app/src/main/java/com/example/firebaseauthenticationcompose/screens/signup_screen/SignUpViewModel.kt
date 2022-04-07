package com.example.firebaseauthenticationcompose.screens.signup_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.firebaseauthenticationcompose.Navigation.Screens
import com.example.firebaseauthenticationcompose.util.LoadingState
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel: ViewModel() {

    var registerState = MutableStateFlow(LoadingState.IDLE)
    private val isRegistered = mutableStateOf(false)

    fun registerWithUsernameAndPassword(email: String, password: String, navController: NavController) = viewModelScope.launch {

        registerState.emit(LoadingState.LOADING)
        Firebase.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                isRegistered.value = true
                navController.navigate(Screens.LoginScreen.route){
                    popUpTo(Screens.LoginScreen.route)
                }
            } else {
                isRegistered.value = false
            }
        }
        if(isRegistered.value) registerState.emit((LoadingState.SUCCESS))
    }
}