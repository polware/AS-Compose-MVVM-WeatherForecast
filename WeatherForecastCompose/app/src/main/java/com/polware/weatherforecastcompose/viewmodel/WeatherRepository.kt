package com.polware.weatherforecastcompose.viewmodel

import android.util.Log
import com.polware.weatherforecastcompose.data.api.ApiResponse
import com.polware.weatherforecastcompose.data.api.WeatherService
import com.polware.weatherforecastcompose.data.models.WeatherResponse
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiService: WeatherService) {

    suspend fun getWeatherData(cityQuery: String, units: String)
        : ApiResponse<WeatherResponse, Boolean, Exception> {

        val response = try {
            apiService.getWeather(query = cityQuery, units = units)
        } catch (exception: Exception) {
            Log.e("ApiException: ", exception.toString())
            return ApiResponse(exception = exception)
        }
        Log.i("ApiResponse: ", response.toString())
        return ApiResponse(data = response)
    }

}