package com.polware.weatherforecastcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.polware.weatherforecastcompose.viewmodel.MainViewModel

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenRoutes.SplashScreen.name) {

        composable(ScreenRoutes.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        val route = ScreenRoutes.MainScreen.name
        composable("$route/{city}",
            arguments = listOf(
                navArgument(name = "city") {
                    type = NavType.StringType
                }
            )) {
            navBackstackEntry ->
            navBackstackEntry.arguments?.getString("city").let {
                city ->
                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController = navController, mainViewModel, city = city)
            }
        }

        composable(ScreenRoutes.SearchScreen.name) {
            SearchScreen(navController = navController)
        }

        composable(ScreenRoutes.FavoritesScreen.name) {
            FavoritesScreen(navController = navController)
        }

        composable(ScreenRoutes.SettingsScreen.name) {
            SettingsScreen(navController = navController)
        }

        composable(ScreenRoutes.AboutScreen.name) {
            AboutScreen(navController = navController)
        }

    }
}