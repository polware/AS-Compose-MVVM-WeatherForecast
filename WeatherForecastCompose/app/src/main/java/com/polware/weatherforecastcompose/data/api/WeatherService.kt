package com.polware.weatherforecastcompose.data.api

import com.polware.weatherforecastcompose.BuildConfig
import com.polware.weatherforecastcompose.data.models.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("forecast/daily")
    suspend fun getWeather(@Query("q") query: String,
                           @Query("units") units: String,
                           @Query("appid") apiKey: String = BuildConfig.API_KEY): WeatherResponse

}