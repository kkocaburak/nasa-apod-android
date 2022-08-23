package com.bkarakoca.data.repository

import com.bkarakoca.data.local.ApodDatasource
import com.bkarakoca.data.mapper.ApodEntityMapper
import com.bkarakoca.data.mapper.ApodFavoriteEntityMapper
import com.bkarakoca.data.mapper.ApodMapper
import com.bkarakoca.data.service.PlanetaryService
import com.bkarakoca.domain.repository.PlanetaryRepository
import com.bkarakoca.domain.uimodel.apod.ApodListUIModel
import com.bkarakoca.domain.uimodel.apod.ApodUIModel
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class PlanetaryRepositoryImpl @Inject constructor(
    private val planetaryService: PlanetaryService,
    private val apodDatasource: ApodDatasource,
    private val apodMapper: ApodMapper,
    private val apodEntityMapper: ApodEntityMapper,
    private val apodFavoriteEntityMapper: ApodFavoriteEntityMapper
) : PlanetaryRepository {

    override suspend fun fetchApodList(): Flow<ApodListUIModel> = callbackFlow {
        val apodList = async { planetaryService.fetchApods() }
        val isUpdated = async { apodDatasource.insertApodEntityList(apodEntityMapper.map(apodList.await())) }

        isUpdated.await()
        trySend(apodMapper.map(apodDatasource.fetchApodListFromDatabase()))

        awaitClose()
    }

    override suspend fun fetchLocalApodList(): Flow<ApodListUIModel> =
        flowOf(apodMapper.map(apodDatasource.fetchApodListFromDatabase()))

    override suspend fun handleApodFavorite(apodUIModel: ApodUIModel) {
        if (apodUIModel.isFavorite) {
            apodDatasource.deleteApodFavorite(apodFavoriteEntityMapper.map(apodUIModel))
        } else {
            apodDatasource.insertApodFavorite(apodFavoriteEntityMapper.map(apodUIModel))
        }
    }
}