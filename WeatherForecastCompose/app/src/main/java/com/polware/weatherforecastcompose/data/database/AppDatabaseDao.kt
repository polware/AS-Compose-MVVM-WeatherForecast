package com.polware.weatherforecastcompose.data.database

import androidx.room.*
import com.polware.weatherforecastcompose.data.models.FavoriteModel
import com.polware.weatherforecastcompose.data.models.UnitsConfig
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDatabaseDao {

    @Query(value = "SELECT * FROM favorite_table")
    fun getFavorites(): Flow<List<FavoriteModel>>

    @Query(value = "SELECT * FROM favorite_table WHERE city =:city")
    suspend fun getFavoriteById(city: String): FavoriteModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoriteModel: FavoriteModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favoriteModel: FavoriteModel)

    @Query(value = "DELETE FROM favorite_table")
    suspend fun deleteAllFavorites()

    @Delete
    suspend fun deleteFavorite(favoriteModel: FavoriteModel)

    // Units configuration
    @Query("SELECT * FROM settings_table")
    fun getUnitsConfig(): Flow<List<UnitsConfig>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnits(units: UnitsConfig)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnits(units: UnitsConfig)

    @Query(value = "DELETE FROM settings_table")
    suspend fun deleteAllUnits()

    @Delete
    suspend fun deleteUnits(units: UnitsConfig)

}