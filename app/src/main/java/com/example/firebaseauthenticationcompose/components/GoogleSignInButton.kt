package com.example.firebaseauthenticationcompose.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.DefaultTintColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firebaseauthenticationcompose.R

//@Preview(showBackground = true)
@Composable
fun GoogleSignInButton(
    isClicked : Boolean,
    onClick: () -> Unit
) {


    OutlinedButton(
        modifier = Modifier
            .height(40.dp),
        contentPadding = PaddingValues(start = 12.dp, end = 16.dp),
        shape = RoundedCornerShape(12.dp),
        onClick = onClick) {
        Row(modifier = Modifier
            .animateContentSize (
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                ))) {
            Icon(
                modifier = Modifier
                    .size(18.dp),
                tint = Color.Unspecified,
                painter = painterResource(R.drawable.google_icon),
                contentDescription = "google icon" )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Sign In with Google",
                color = Color.Black,
            )
            Spacer(modifier = Modifier.width(8.dp))
            if(isClicked)
            CircularProgressIndicator(
                modifier = Modifier.size(18.dp),
                color = Color(0xFF03A9F4),
                strokeWidth = 2.5.dp
            )

        }
    }
    
}