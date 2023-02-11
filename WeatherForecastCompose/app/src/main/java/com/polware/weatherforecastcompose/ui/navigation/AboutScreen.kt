package com.polware.weatherforecastcompose.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.polware.weatherforecastcompose.R
import com.polware.weatherforecastcompose.ui.components.MainAppBar

@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(
        topBar = {
            MainAppBar(title = "About",
                backgroundColor = Color(0xFFb3cde0),
                icon = Icons.Default.ArrowBack,
                isMainScreen = false,
                navController = navController) {
                navController.popBackStack()
            }
        }
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(text = stringResource(id = R.string.about_app),
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(text = stringResource(id = R.string.api_source),
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Light
                )
                Text(text = stringResource(id = R.string.developed_by),
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}