package com.example.taskthreeweeklyseven.presentation.utils

import com.example.taskthreeweeklyseven.data.model.Cat
import com.example.taskthreeweeklyseven.data.dto.ImageResponce

sealed class AppState {
    data class SuccessMain(val imageResponse: List<ImageResponce>) : AppState()
    data class SuccessFavorite(val favoriteData: List<Cat>) : AppState()
    data class Error(val e: Throwable) : AppState()
}