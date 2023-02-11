package com.polware.weatherforecastcompose.ui.navigation

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.polware.weatherforecastcompose.data.api.ApiResponse
import com.polware.weatherforecastcompose.data.models.WeatherResponse
import com.polware.weatherforecastcompose.ui.components.MainContent
import com.polware.weatherforecastcompose.viewmodel.MainViewModel
import com.polware.weatherforecastcompose.viewmodel.SettingsViewModel

@Composable
fun MainScreen(navController: NavController,
               mainViewModel: MainViewModel = hiltViewModel(),
               settingsViewModel: SettingsViewModel = hiltViewModel(),
               city: String?) {

    val unitsFromDB = settingsViewModel.unitsList.collectAsState().value
    var units by remember {
        mutableStateOf("metric")
    }
    var metricStatus by remember {
        mutableStateOf(false)
    }

    if (!unitsFromDB.isNullOrEmpty()) {
        units = unitsFromDB[0].unit.split(" ")[0].lowercase()
        metricStatus = units == "metric"
        val weatherData = produceState<ApiResponse<WeatherResponse, Boolean, Exception>>(
            initialValue = ApiResponse(loading = true)) {
            value = mainViewModel.getWeatherData(city = city!!, units = units)
        }.value

        if (weatherData.loading == true)
            CircularProgressIndicator()
        else if (weatherData.data != null) {
            MainContent(weatherData = weatherData.data!!, navController = navController, metricStatus = metricStatus)
        }
    }

}