package com.bkarakoca.data.local

import com.bkarakoca.data.model.ApodEntity
import com.bkarakoca.data.model.ApodFavoriteEntity
import javax.inject.Inject

class ApodDatasource @Inject constructor(
    private val apodDao: ApodDao,
) {

    fun insertApodEntityList(apodList: List<ApodEntity>) {
        apodDao.insertApodEntityList(apodList)
    }

    fun insertApodFavorite(apodFavoriteIdEntity: ApodFavoriteEntity) {
        apodDao.insertFavoriteApodEntity(apodFavoriteIdEntity)
    }

    fun deleteApodFavorite(apodFavoriteEntity: ApodFavoriteEntity) {
        apodDao.deleteFavoriteApodEntity(apodFavoriteEntity.name)
    }

    fun updateApodList(apodEntityList: List<ApodEntity>) {
        apodDao.updateApodEntityList(apodEntityList)
    }

    fun fetchApodListFromDatabase(): List<ApodEntity> {
        val apodList = apodDao.fetchApodList()
        val favoriteApodIdList = apodDao.fetchFavoriteApodIdList()

        apodList.forEach { apod ->
            apod.isFavorite = favoriteApodIdList.find { favoriteApod ->
                favoriteApod.name == apod.title // there was no id in apod response model
            } != null
        }

        return apodList
    }

}