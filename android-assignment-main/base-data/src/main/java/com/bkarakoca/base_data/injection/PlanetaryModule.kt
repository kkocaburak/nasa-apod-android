package com.bkarakoca.base_data.injection

import android.content.Context
import androidx.room.Room
import com.bkarakoca.data.local.ApodDao
import com.bkarakoca.data.local.ApodDatabase
import com.bkarakoca.data.local.ApodDatasource
import com.bkarakoca.data.mapper.ApodEntityMapper
import com.bkarakoca.data.mapper.ApodFavoriteEntityMapper
import com.bkarakoca.data.mapper.ApodMapper
import com.bkarakoca.data.repository.PlanetaryRepositoryImpl
import com.bkarakoca.data.service.PlanetaryService
import com.bkarakoca.domain.repository.PlanetaryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        apodDatasource: ApodDatasource,
        apodMapper: ApodMapper,
        apodEntityMapper: ApodEntityMapper,
        apodFavoriteEntityMapper: ApodFavoriteEntityMapper
    ): PlanetaryRepository {
        return PlanetaryRepositoryImpl(
            planetaryService,
            apodDatasource,
            apodMapper,
            apodEntityMapper,
            apodFavoriteEntityMapper
        )
    }

    @Provides
    @Singleton
    fun provideApodDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ApodDatabase::class.java, "ApodDatabase")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideApodDao(appDatabase: ApodDatabase): ApodDao {
        return appDatabase.apodDao
    }

}