package com.example.taskthreeweeklyseven.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskthreeweeklyseven.data.db.DataBaseFactory
import com.example.taskthreeweeklyseven.presentation.utils.AppState
import com.example.taskthreeweeklyseven.presentation.utils.mapFavorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatViewModel : ViewModel() {

    private val dataBase = DataBaseFactory.create().favoriteDao()
    private val data = MutableLiveData<AppState>()

    val liveData: LiveData<AppState> = data

    fun getData() {
        viewModelScope.launch { loadData() }
    }

    private suspend fun loadData() = withContext(Dispatchers.IO) {
        try {
            val data = dataBase.getFavoriteList()
            this@CatViewModel.data.postValue(AppState.SuccessFavorite(mapFavorite(data)))
        } catch (e: Throwable) {
            data.postValue(AppState.Error(e))
        }
    }
}