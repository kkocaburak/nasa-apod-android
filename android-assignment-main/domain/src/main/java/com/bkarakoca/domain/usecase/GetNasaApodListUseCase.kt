package com.bkarakoca.domain.usecase

import com.bkarakoca.domain.repository.PlanetaryRepository
import com.bkarakoca.domain.uimodel.apod.ApodHeaderModel
import com.bkarakoca.domain.uimodel.apod.ApodListUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNasaApodListUseCase @Inject constructor(
    private val planetaryRepository: PlanetaryRepository
) {

    companion object {
        const val FIRST_POSITION = 0
    }

    suspend operator fun invoke(): Flow<ApodListUIModel> = flow {
        planetaryRepository.fetchApodList().collect {
            emit(
                it.apply {
                    apodListLatest.add(FIRST_POSITION, ApodHeaderModel(0, "Latest"))
                    apodListFavorites.add(FIRST_POSITION, ApodHeaderModel(0, "My Favorites"))
                }
            )
        }
    }

}