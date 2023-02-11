package com.polware.weatherforecastcompose.ui.navigation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.polware.weatherforecastcompose.data.models.UnitsConfig
import com.polware.weatherforecastcompose.ui.components.MainAppBar
import com.polware.weatherforecastcompose.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(navController: NavController,
                   settingsViewModel: SettingsViewModel = hiltViewModel()) {

    var unitsToggleState by remember {
        mutableStateOf(false)
    }
    val measurementUnits = listOf("Metric (C)", "Imperial (F)")
    val choiceFromDB = settingsViewModel.unitsList.collectAsState().value
    val defaultChoice = if (choiceFromDB.isNullOrEmpty()) measurementUnits[0]
                        else choiceFromDB[0].unit
    var choiceState by remember {
        mutableStateOf(defaultChoice)
    }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            MainAppBar(title = "Settings",
                backgroundColor = Color(0xFFb3cde0),
                icon = Icons.Default.ArrowBack,
                isMainScreen = false,
                navController = navController
            ) { navController.popBackStack() }
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Select units of measurement:",
                    modifier = Modifier
                        .padding(bottom = 15.dp)
                )
                IconToggleButton(
                    checked = !unitsToggleState,
                    onCheckedChange = {
                        unitsToggleState = !it
                        choiceState = if (unitsToggleState) {
                            "Imperial (F)"
                        } else {
                            "Metric (C)"
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .clip(shape = RectangleShape)
                        .padding(5.dp)
                        .background(Color(0xFFb3cde0))
                ) {
                    Text(
                        text = if (unitsToggleState) "Fahrenheit °F"
                                else "Celsius °C",
                        color = Color.DarkGray
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        settingsViewModel.deleteAllUnits()
                        settingsViewModel.insertUnits(UnitsConfig(unit = choiceState))
                        Toast.makeText(context, "Settings saved", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    },
                    shape = CutCornerShape(3.dp),
                    contentPadding = PaddingValues(8.dp),
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color(0xFF009688),
                        contentColor = Color.White
                    )
                ) {
                    Text("Save",
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
    }
}