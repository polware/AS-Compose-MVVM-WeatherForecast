package com.polware.weatherforecastcompose.ui.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.polware.weatherforecastcompose.data.models.FavoriteModel
import com.polware.weatherforecastcompose.viewmodel.FavoritesDBViewModel

@Composable
fun MainAppBar(title: String, icon: ImageVector? = null,
               backgroundColor: Color,
               isMainScreen: Boolean = true, elevation: Dp = 0.dp,
               navController: NavController,
               favoritesDBViewModel: FavoritesDBViewModel = hiltViewModel(),
               onSearchClick: () -> Unit = {},
               onMoreOptionsClick: () -> Unit = {}) {

    val context = LocalContext.current
    val showMenuOptions = remember {
        mutableStateOf(false)
    }
    if (showMenuOptions.value) {
        PopupMenu(showMenuOptions = showMenuOptions, navController = navController
        )
    }

    TopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colors.onSecondary,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp)
            )
        },
        actions = {
            if (isMainScreen) {
                IconButton(onClick = {
                    onSearchClick.invoke()
                }
                ) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search icon")
                }
                IconButton(onClick = {
                    showMenuOptions.value = true
                }
                ) {
                    Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "More icon")
                }
            }
            else {
                Box { }
            }
        },
        navigationIcon = {
            if (icon != null) {
                Icon(imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSecondary,
                    modifier = Modifier.clickable {
                        onMoreOptionsClick.invoke()
                    }
                )
            }
            if (isMainScreen) {
                val isAddedToFavorites = favoritesDBViewModel.favoriteList.collectAsState().value.filter {
                    item ->
                    (item.city == title.split(",")[0])
                }
                if (isAddedToFavorites.isNullOrEmpty()) {
                    Icon(imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite icon",
                        modifier = Modifier
                            .scale(0.9f)
                            .clickable {
                                val textTitle = title.split(",")
                                favoritesDBViewModel.insertFavorite(
                                    FavoriteModel(
                                        city = textTitle[0],
                                        country = textTitle[1]
                                    )
                                )
                                Toast.makeText(context, "City added to favorites",
                                        Toast.LENGTH_SHORT).show()
                            },
                        tint = Color.Red.copy(alpha = 0.6f)
                    )
                }
                else {
                    Box { }
                }
            }
        },
        backgroundColor = backgroundColor,
        elevation = elevation
    )
}