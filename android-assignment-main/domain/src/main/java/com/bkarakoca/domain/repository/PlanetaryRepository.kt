package com.bkarakoca.domain.repository

import com.bkarakoca.domain.uimodel.apod.ApodListUIModel
import com.bkarakoca.domain.uimodel.apod.ApodUIModel
import kotlinx.coroutines.flow.Flow

interface PlanetaryRepository {

    suspend fun fetchApodList(): Flow<ApodListUIModel>

    suspend fun fetchLocalApodList(): Flow<ApodListUIModel>

    suspend fun handleApodFavorite(apodUIModel: ApodUIModel)

}