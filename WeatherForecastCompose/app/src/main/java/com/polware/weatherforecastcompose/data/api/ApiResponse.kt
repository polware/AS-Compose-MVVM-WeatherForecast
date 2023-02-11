package com.polware.weatherforecastcompose.data.api

class ApiResponse<T, Boolean, E: Exception>(
    var data: T? = null,
    var loading: Boolean? = null,
    var exception: E? = null
    )