package com.example.firebaseauthenticationcompose.screens.login_screen

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.firebaseauthenticationcompose.Navigation.Screens
import com.example.firebaseauthenticationcompose.util.LoadingState
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginViewModel(): ViewModel() {

    val loadingState = MutableStateFlow(LoadingState.IDLE)
    private lateinit var _auth: FirebaseAuth
    private val loginState = mutableStateOf(false)

    init {
        _auth = Firebase.auth
    }

    fun signInWithEmailAndPassword(email: String, password: String, navController: NavController) = viewModelScope.launch {
        try {
            loadingState.emit(LoadingState.LOADING)
            _auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    loginState.value = true
                    navController.navigate(Screens.HomeScreen.route){
                        popUpTo(Screens.LaunchingNavigation.route)
                    }
                } else {
                    loginState.value = false
                }
            }
            if(loginState.value) loadingState.emit(LoadingState.SUCCESS)
        } catch (exception: Exception) {
            Log.d("Firebase Authentication", "signInWithEmailAndPassword: ${exception.localizedMessage}")
        }
    }
}