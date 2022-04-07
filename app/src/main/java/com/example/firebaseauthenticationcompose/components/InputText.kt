package com.example.firebaseauthenticationcompose.components

import android.text.InputType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.firebaseauthenticationcompose.R


//@Preview(showBackground = true)
@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    text: String,
    label: String = "Helloo",
    leadingIcon: Int,
    inputType: KeyboardType = KeyboardType.Number,
    visualTransformation: VisualTransformation,
    onTextChange: (String) -> Unit,
) {


    OutlinedTextField(
        modifier = modifier,
        label = { Text(label) },
        value = text,
        onValueChange = onTextChange,
        leadingIcon = { Icon(
            painter = painterResource(id = leadingIcon), contentDescription = "" ) },
        keyboardOptions = KeyboardOptions(
            keyboardType = inputType
        ),
        singleLine = true,
        visualTransformation = visualTransformation,

    )

}