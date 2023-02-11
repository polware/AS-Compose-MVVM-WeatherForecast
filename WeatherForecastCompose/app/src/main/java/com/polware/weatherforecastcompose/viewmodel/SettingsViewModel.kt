package com.polware.weatherforecastcompose.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polware.weatherforecastcompose.data.models.UnitsConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val repository: AppDatabaseRepository)
    : ViewModel() {

    private val _unitsList = MutableStateFlow<List<UnitsConfig>>(emptyList())
    val unitsList = _unitsList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUnits().distinctUntilChanged().collect {
                    listOfUnits ->
                    if (listOfUnits.isNullOrEmpty()) {
                        Log.i("Settings: ", "Empty config")
                    }else {
                        _unitsList.value = listOfUnits
                    }
                }
        }
    }

    fun insertUnits(units: UnitsConfig) = viewModelScope.launch {
        repository.insertUnits(units)
    }

    fun updateUnits(units: UnitsConfig) = viewModelScope.launch {
        repository.updateUnits(units)
    }

    fun deleteUnits(units: UnitsConfig) = viewModelScope.launch {
        repository.deleteUnits(units)
    }

    fun deleteAllUnits() = viewModelScope.launch {
        repository.deleteAllUnits()
    }

}