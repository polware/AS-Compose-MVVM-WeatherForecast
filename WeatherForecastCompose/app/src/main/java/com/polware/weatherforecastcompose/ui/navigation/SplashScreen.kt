package com.polware.weatherforecastcompose.ui.navigation

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.polware.weatherforecastcompose.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val defaultCity = "Bogota"
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true,
        block = {
            scale.animateTo(targetValue = 0.9f,
                animationSpec = tween(durationMillis = 800, easing = {
                    OvershootInterpolator(8f).getInterpolation(it)
                })
            )
            delay(2000L)
            navController.navigate(ScreenRoutes.MainScreen.name +"/$defaultCity")
        }
    )

    Surface(modifier = Modifier
        .padding(15.dp)
        .size(320.dp)
        .scale(scale.value),
        shape = CircleShape,
        color = Color(0xFF01579B),
        border = BorderStroke(width = 3.dp, color = Color(0xFFFFBB54))
    ) {

        Column(
            modifier = Modifier
                .padding(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_app),
                contentDescription = "Logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(180.dp)
            )
            Text(text = "Weather Forecast",
                color = Color(0xFFFFBB54),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
        }
    }
}