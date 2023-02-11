package com.polware.weatherforecastcompose.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polware.weatherforecastcompose.data.models.FavoriteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesDBViewModel @Inject constructor(private val repository: AppDatabaseRepository)
    : ViewModel() {

    private val _favoriteList = MutableStateFlow<List<FavoriteModel>>(emptyList())
    val favoriteList = _favoriteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavorites().distinctUntilChanged().collect {
                if (it.isNullOrEmpty()) {
                    Log.e("FavViewModel: ", "Empty favorites")
                }
                else {
                    _favoriteList.value = it
                    Log.i("FavoritesList: ", "${favoriteList.value}")
                }
            }
        }
    }

    fun insertFavorite(favoriteModel: FavoriteModel) = viewModelScope.launch {
        repository.insertFavorite(favoriteModel)
    }

    fun updateFavorite(favoriteModel: FavoriteModel) = viewModelScope.launch {
        repository.updateFavorite(favoriteModel)
    }

    fun deleteFavorite(favoriteModel: FavoriteModel) = viewModelScope.launch {
        repository.deleteFavorite(favoriteModel)
    }

    fun deleteAllFavorites() = viewModelScope.launch {
        repository.deleteAllFavorites()
    }

}