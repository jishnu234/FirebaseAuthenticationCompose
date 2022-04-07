package com.example.firebaseauthenticationcompose.screens.launching_navigation

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.firebaseauthenticationcompose.R
import com.example.firebaseauthenticationcompose.Navigation.Screens
import com.example.firebaseauthenticationcompose.components.GoogleSignInButton
import com.example.firebaseauthenticationcompose.components.LottieAnimation.LottieAnim
import com.example.firebaseauthenticationcompose.util.LoadingState
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun LaunchingNavigation(
    navController: NavController,
    viewModel: LaunchingViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    var isClicked = remember {
        mutableStateOf(false)
    }

    val state by viewModel.loadingState.collectAsState()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val credentials = GoogleAuthProvider.getCredential(account.idToken, null)
            viewModel.signInUserUsingGoogle(credentials, navController)
        } catch (exception: Exception) {
            Log.d("Firebase Login", "LoginScreen: ${exception.localizedMessage}")
        }
    }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
        ) {
            LottieAnim(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                animation = R.raw.car_lottie
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight(0.5f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.70f)
                        .padding(top = 20.dp)
                        .height(40.dp),
                    shape = RoundedCornerShape(12.dp),
                    onClick = { navController.navigate(Screens.LoginScreen.route) },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.DarkGray,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "SIGN IN")
                }
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.70f)
                        .height(40.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.DarkGray,
                        contentColor = Color.White
                    ),
                    onClick = {
                        navController.navigate(Screens.SignUpScreen.route)
                    }) {
                    Text(text = "SIGN UP")
                }
                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                )
                val context = LocalContext.current
                val token =
                    "408906382898-3fkblql29r3r80rpe55257vqts7lvt95.apps.googleusercontent.com"
                GoogleSignInButton(isClicked = (viewModel.loadingState.collectAsState().value == LoadingState.LOADING)) {

                    Log.d("Firebase Login", "LaunchingNavigation: Clickedd")
                    val gso = GoogleSignInOptions.Builder(
                        GoogleSignInOptions.DEFAULT_SIGN_IN
                    )
                        .requestIdToken(token)
                        .requestEmail()
                        .build()

                    val googleSIgnInClient = GoogleSignIn.getClient(context, gso)
                    launcher.launch(googleSIgnInClient.signInIntent)
                }

            }
        }
    }
