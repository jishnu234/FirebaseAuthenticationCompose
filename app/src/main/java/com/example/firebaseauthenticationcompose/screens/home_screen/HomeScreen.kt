package com.example.firebaseauthenticationcompose.screens.home_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.firebaseauthenticationcompose.Navigation.Screens
import com.example.firebaseauthenticationcompose.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


//@Preview(showBackground = true)
@Composable
fun HomeScreen(navController: NavController) {

//    Toast.makeText(LocalContext.current, "Home screen", Toast.LENGTH_SHORT).show()
    Log.d("TAG", "HomeScreen: called")
    val context = LocalContext.current
    Column(modifier = Modifier
        .fillMaxSize(),
    ) {
        Icon(
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.End)
                .clip(CircleShape)
                .padding(4.dp)
                .clickable {
                    Firebase.auth.signOut()
                    navController.popBackStack()
                    Toast.makeText(context, "Successfully Signed Out", Toast.LENGTH_SHORT).show()
                },
            painter = painterResource(id = R.drawable.ic_signout),
            contentDescription = "Sign out",
            tint = Color.Black
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = Firebase.auth.currentUser?.email.toString())
        }
    }
}