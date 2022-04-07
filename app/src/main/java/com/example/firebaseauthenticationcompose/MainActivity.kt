package com.example.firebaseauthenticationcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.firebaseauthenticationcompose.Navigation.Navigation
import com.example.firebaseauthenticationcompose.screens.launching_navigation.LaunchingNavigation
import com.example.firebaseauthenticationcompose.screens.login_screen.LoginScreen
import com.example.firebaseauthenticationcompose.ui.theme.FirebaseAuthenticationComposeTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseAuthenticationComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val auth = Firebase.auth
                    val currentUser = auth.currentUser
                        Navigation(currentUser)

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FirebaseAuthenticationComposeTheme {
        Greeting("Android")
    }
}