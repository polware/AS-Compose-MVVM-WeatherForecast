package com.polware.weatherforecastcompose.data.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class FavoriteModel(
    @NonNull
    @PrimaryKey
    val city: String,
    val country: String,
)
