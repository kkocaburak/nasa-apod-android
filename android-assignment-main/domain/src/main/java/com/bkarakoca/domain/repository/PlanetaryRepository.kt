package com.bkarakoca.domain.repository

import com.bkarakoca.domain.uimodel.ApodListUIModel
import kotlinx.coroutines.flow.Flow

interface PlanetaryRepository {
    suspend fun fetchApodList(): Flow<ApodListUIModel>
}