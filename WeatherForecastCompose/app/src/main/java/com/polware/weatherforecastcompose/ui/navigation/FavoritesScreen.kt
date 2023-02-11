package com.polware.weatherforecastcompose.ui.navigation

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.polware.weatherforecastcompose.ui.components.MainAppBar
import com.polware.weatherforecastcompose.viewmodel.FavoritesDBViewModel

@Composable
fun FavoritesScreen(navController: NavController,
                    favoritesDBViewModel: FavoritesDBViewModel = hiltViewModel()) {

    val context = LocalContext.current
    Scaffold(
        topBar = {
            MainAppBar(
                title = "Favorite Cities",
                backgroundColor = Color(0xFFb3cde0),
                icon = Icons.Default.ArrowBack,
                isMainScreen = false,
                navController = navController
            ) {
                navController.popBackStack()
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val listFavorites = favoritesDBViewModel.favoriteList.collectAsState().value
                if (listFavorites.isNullOrEmpty()) {
                    Text(text = "Empty favorites list!", fontWeight = FontWeight.Bold)
                }
                else {
                    LazyColumn {
                        items(items = listFavorites) {
                            // Row for every city
                            Surface(
                                modifier = Modifier
                                    .padding(3.dp)
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .clickable {
                                        navController
                                            .navigate(ScreenRoutes.MainScreen.name + "/${it.city}")
                                    },
                                shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
                                color = Color(0xFFb3cde0)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Text(
                                        text = it.city,
                                        modifier = Modifier.padding(start = 4.dp),
                                        style = MaterialTheme.typography.subtitle2
                                    )
                                    Surface(
                                        modifier = Modifier.padding(0.dp),
                                        shape = CircleShape,
                                        color = Color(0xFF8AE2D8)
                                    ) {
                                        Text(
                                            text = it.country,
                                            modifier = Modifier.padding(4.dp),
                                            style = MaterialTheme.typography.caption
                                        )
                                    }
                                    Icon(
                                        imageVector = Icons.Rounded.Delete,
                                        contentDescription = "Delete icon",
                                        modifier = Modifier.clickable {
                                            favoritesDBViewModel.deleteFavorite(it)
                                            Toast.makeText(context, "City deleted!",
                                                Toast.LENGTH_SHORT).show()
                                        },
                                        tint = Color.Red.copy(alpha = 0.6f)
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {
                            favoritesDBViewModel.deleteAllFavorites()
                            Toast.makeText(context, "Removed all cities!",
                                Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        },
                        shape = CutCornerShape(3.dp),
                        contentPadding = PaddingValues(8.dp),
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = Color.Red.copy(alpha = 0.6f),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Delete All",
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
            }
        }
    }
}