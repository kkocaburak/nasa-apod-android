package com.bkarakoca.domain.usecase

import com.bkarakoca.domain.repository.PlanetaryRepository
import com.bkarakoca.domain.uimodel.apod.ApodUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class HandleApodFavoriteUseCase @Inject constructor(
    private val planetaryRepository: PlanetaryRepository
) {

    companion object {
        const val FIRST_POSITION = 0
    }

    suspend operator fun invoke(apodUIModel: ApodUIModel): Flow<Unit> =
        flowOf(planetaryRepository.handleApodFavorite(apodUIModel))

}