package com.polware.weatherforecastcompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.polware.weatherforecastcompose.data.models.FavoriteModel
import com.polware.weatherforecastcompose.data.models.UnitsConfig

@Database(entities = [FavoriteModel::class, UnitsConfig::class], version = 1, exportSchema = false)
abstract class ApplicationDatabase: RoomDatabase() {

    abstract fun appDatabaseDao(): AppDatabaseDao

}