package com.bkarakoca.base_data.injection

import com.bkarakoca.data.mapper.ApodPicturesMapper
import com.bkarakoca.data.repository.PlanetaryRepositoryImpl
import com.bkarakoca.data.service.PlanetaryService
import com.bkarakoca.domain.repository.PlanetaryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class PlanetaryModule {

    @Provides
    @Singleton
    fun providePlanetaryService(
        retrofit: Retrofit
    ): PlanetaryService {
        return retrofit.create(PlanetaryService::class.java)
    }

    @Provides
    @Singleton
    fun providePlanetaryRepository(
        planetaryService: PlanetaryService,
        mapper: ApodPicturesMapper
    ): PlanetaryRepository {
        return PlanetaryRepositoryImpl(planetaryService, mapper)
    }

}