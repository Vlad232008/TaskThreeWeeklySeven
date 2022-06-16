package com.example.taskthreeweeklyseven.presentation.utils

import com.example.taskthreeweeklyseven.data.db.entity.FavoriteItem
import com.example.taskthreeweeklyseven.data.dto.ImageResponce
import com.example.taskthreeweeklyseven.data.model.Cat

fun mapImage(imageResponse: ImageResponce): FavoriteItem {
    return FavoriteItem(
        imageResponse.id,
        imageResponse.url,
    )
}

fun mapFavorite(favoriteItemList: List<FavoriteItem>): List<Cat> {
    return favoriteItemList.map {
        Cat(it.id, it.image)
    }
}