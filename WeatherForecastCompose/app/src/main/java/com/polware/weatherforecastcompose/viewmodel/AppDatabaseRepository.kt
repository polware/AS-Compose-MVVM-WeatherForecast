package com.polware.weatherforecastcompose.viewmodel

import com.polware.weatherforecastcompose.data.database.AppDatabaseDao
import com.polware.weatherforecastcompose.data.models.FavoriteModel
import com.polware.weatherforecastcompose.data.models.UnitsConfig
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppDatabaseRepository @Inject constructor(private val appDatabaseDao: AppDatabaseDao) {

    fun getFavorites(): Flow<List<FavoriteModel>> = appDatabaseDao.getFavorites()

    suspend fun getFavoriteById(city: String): FavoriteModel = appDatabaseDao.getFavoriteById(city)

    suspend fun insertFavorite(favoriteModel: FavoriteModel) =
        appDatabaseDao.insertFavorite(favoriteModel)

    suspend fun updateFavorite(favoriteModel: FavoriteModel) =
        appDatabaseDao.updateFavorite(favoriteModel)

    suspend fun deleteAllFavorites() = appDatabaseDao.deleteAllFavorites()

    suspend fun deleteFavorite(favoriteModel: FavoriteModel) =
        appDatabaseDao.deleteFavorite(favoriteModel)

    // Units config
    fun getUnits(): Flow<List<UnitsConfig>> = appDatabaseDao.getUnitsConfig()

    suspend fun insertUnits(units: UnitsConfig) = appDatabaseDao.insertUnits(units)

    suspend fun updateUnits(units: UnitsConfig) = appDatabaseDao.updateUnits(units)

    suspend fun deleteUnits(units: UnitsConfig) = appDatabaseDao.deleteUnits(units)

    suspend fun deleteAllUnits() = appDatabaseDao.deleteAllUnits()

}