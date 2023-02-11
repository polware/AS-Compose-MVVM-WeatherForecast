package com.polware.weatherforecastcompose.di

import android.content.Context
import androidx.room.Room
import com.polware.weatherforecastcompose.BuildConfig
import com.polware.weatherforecastcompose.data.database.ApplicationDatabase
import com.polware.weatherforecastcompose.data.database.AppDatabaseDao
import com.polware.weatherforecastcompose.data.api.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModules {

    @Singleton
    @Provides
    fun provideWeatherApi(): WeatherService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)
    }

    @Singleton
    @Provides
    fun provideFavoritesDao(applicationDatabase: ApplicationDatabase):
            AppDatabaseDao = applicationDatabase.appDatabaseDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context):
            ApplicationDatabase = Room.databaseBuilder(context, ApplicationDatabase::class.java,
        "favorite_database").fallbackToDestructiveMigration().build()

}