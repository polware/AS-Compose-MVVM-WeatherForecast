package com.polware.weatherforecastcompose.data.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings_table")
data class UnitsConfig(
    @NonNull
    @PrimaryKey
    val unit: String
)
