package com.bkarakoca.data.local

import androidx.room.*
import com.bkarakoca.data.model.ApodEntity
import com.bkarakoca.data.model.ApodFavoriteEntity

@Dao
interface ApodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertApodEntity(entity: ApodEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertApodEntityList(entity: List<ApodEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteApodEntity(apodFavoriteEntity: ApodFavoriteEntity)

    @Update
    fun updateApodEntityList(entity: List<ApodEntity>)

    @Query("DELETE FROM apodFavoriteIdEntity WHERE name = :favoriteName")
    fun deleteFavoriteApodEntity(favoriteName: String)

    @Query("Select * from apodEntity")
    fun fetchApodList(): List<ApodEntity>

    @Query("Select * from apodFavoriteIdEntity")
    fun fetchFavoriteApodIdList(): List<ApodFavoriteEntity>

}