package com.polware.weatherforecastcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.polware.weatherforecastcompose.ui.navigation.ScreenRoutes

@Composable
fun PopupMenu(showMenuOptions: MutableState<Boolean>, navController: NavController) {
    var expanded by remember {
        mutableStateOf(true)
    }
    val options = listOf("Favorites", "Settings", "About")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 40.dp, right = 20.dp)
    ) {
        DropdownMenu(expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier
                .width(140.dp)
                .background(Color.White)
        ) {
            options.forEachIndexed {
                    _, optionText ->
                DropdownMenuItem(onClick = {
                    showMenuOptions.value = false
                }
                ) {
                    Icon(
                        imageVector = when (optionText) {
                            "Favorites" -> Icons.Default.FavoriteBorder
                            "Settings" -> Icons.Default.Settings
                            else -> Icons.Default.Info
                        },
                        contentDescription = "",
                        tint = Color.LightGray
                    )
                    Text(text = optionText, modifier = Modifier
                        .clickable {
                            navController.navigate(
                                when (optionText) {
                                    "Favorites" -> ScreenRoutes.FavoritesScreen.name
                                    "Settings" -> ScreenRoutes.SettingsScreen.name
                                    else -> ScreenRoutes.AboutScreen.name
                                }
                            )
                        },
                        fontWeight = FontWeight.W300
                    )
                }
            }
        }
    }
}