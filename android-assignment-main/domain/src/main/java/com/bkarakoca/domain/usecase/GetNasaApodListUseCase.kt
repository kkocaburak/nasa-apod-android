package com.bkarakoca.domain.usecase

import com.bkarakoca.domain.repository.PlanetaryRepository
import javax.inject.Inject

class GetNasaApodListUseCase @Inject constructor(
    private val planetaryRepository: PlanetaryRepository
) {

    suspend operator fun invoke() = planetaryRepository.fetchApodList()

}