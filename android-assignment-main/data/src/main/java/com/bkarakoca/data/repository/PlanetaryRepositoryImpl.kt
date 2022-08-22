package com.bkarakoca.data.repository

import com.bkarakoca.data.mapper.ApodPicturesMapper
import com.bkarakoca.data.service.PlanetaryService
import com.bkarakoca.domain.repository.PlanetaryRepository
import com.bkarakoca.domain.uimodel.ApodListUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlanetaryRepositoryImpl @Inject constructor(
    private val planetaryService: PlanetaryService,
    private val mapper: ApodPicturesMapper
) : PlanetaryRepository {

    override suspend fun fetchApodList(): Flow<ApodListUIModel> = flow {
        println("::PlanetaryRepositoryImpl = " + planetaryService.fetchApods())
        emit(mapper.map(planetaryService.fetchApods()))
    }

}