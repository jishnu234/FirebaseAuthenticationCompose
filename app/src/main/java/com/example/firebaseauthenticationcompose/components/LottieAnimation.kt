package com.example.firebaseauthenticationcompose.components.LottieAnimation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.*
import android.R.raw
import androidx.annotation.RawRes
import androidx.compose.ui.Modifier
import com.example.firebaseauthenticationcompose.R


@Composable
fun LottieAnim(modifier: Modifier = Modifier, animation: Int) {
    val compositionRaw by rememberLottieComposition(LottieCompositionSpec.RawRes(animation))
    val progress = animateLottieCompositionAsState(
        composition = compositionRaw,
        isPlaying = true,
        iterations = LottieConstants.IterateForever,
        speed = 1f
    )

    LottieAnimation(
        modifier = modifier,
        composition = compositionRaw,
        progress = progress.value
    )

}