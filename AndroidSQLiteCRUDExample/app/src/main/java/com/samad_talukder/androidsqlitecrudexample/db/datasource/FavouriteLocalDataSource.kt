package com.samad_talukder.androidsqlitecrudexample.db.datasource

import com.samad_talukder.androidsqlitecrudexample.db.model.FavouriteEntity

interface FavouriteLocalDataSource {
    fun getFavouriteList(): List<FavouriteEntity>

    fun getFavouriteItem(id: Int): FavouriteEntity?

    fun saveFavouriteItem(favouriteEntityList: List<FavouriteEntity>)

    fun saveFavouriteItem(favouriteEntityList: FavouriteEntity): Boolean?

    fun updateFavouriteItem(favouriteEntityList: FavouriteEntity): Boolean?

    fun deleteFavouriteItem(id: Int)

    fun deleteAllFavouriteItem()
}