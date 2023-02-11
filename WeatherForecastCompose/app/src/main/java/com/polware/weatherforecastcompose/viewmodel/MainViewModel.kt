package com.polware.weatherforecastcompose.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polware.weatherforecastcompose.data.api.ApiResponse
import com.polware.weatherforecastcompose.data.models.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository): ViewModel() {

    suspend fun getWeatherData(city: String, units: String): ApiResponse<WeatherResponse, Boolean, Exception> {
        return repository.getWeatherData(cityQuery = city, units = units)
    }

}