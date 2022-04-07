package com.example.firebaseauthenticationcompose.screens.signup_screen

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.firebaseauthenticationcompose.R
import com.example.firebaseauthenticationcompose.components.InputTextField
import com.example.firebaseauthenticationcompose.components.LottieAnimation.LottieAnim

@Composable
fun SignUpScreen(navController: NavController,
                 viewModel: SignUpViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    var emailState by remember {
        mutableStateOf("")
    }
    var passwordState by remember {
        mutableStateOf("")
    }


    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 12.dp),
            text = "Register",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 26.sp,
            color = Color.Black
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .fillMaxHeight(0.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            LottieAnim(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                animation = R.raw.car_animation)
            InputTextField(
                label = "Email",
                text =  emailState,
                leadingIcon = R.drawable.ic_email,
                modifier = Modifier.fillMaxWidth(),
                inputType = KeyboardType.Email,
                visualTransformation = VisualTransformation.None
            ){
                emailState = it
            }
            Spacer(modifier = Modifier
                .height(8.dp))
            InputTextField(
                label = "Password",
                text =  passwordState,
                leadingIcon = R.drawable.ic_password,
                modifier = Modifier.fillMaxWidth(),
                inputType = KeyboardType.NumberPassword,
                visualTransformation = PasswordVisualTransformation()
            ){
                passwordState = it.take(8)
            }
            Spacer(modifier = Modifier
                .height(32.dp))

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
                    if(emailState.isNotEmpty() && passwordState.isNotEmpty()){
                        viewModel.registerWithUsernameAndPassword(email = emailState.trim(),
                            password = passwordState.trim(), navController = navController)
                    }

                }) {
                Text(text = "Register")
            }
        }
    }
}