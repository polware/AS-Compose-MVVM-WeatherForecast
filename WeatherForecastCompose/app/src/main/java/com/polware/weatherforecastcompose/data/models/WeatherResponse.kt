package com.polware.weatherforecastcompose.data.models

data class WeatherResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForecastList>,
    val message: Double
)