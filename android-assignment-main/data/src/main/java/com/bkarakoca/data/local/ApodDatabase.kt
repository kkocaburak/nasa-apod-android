package com.bkarakoca.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bkarakoca.data.model.ApodEntity
import com.bkarakoca.data.model.ApodFavoriteEntity

@Database(
    entities = [ApodEntity::class, ApodFavoriteEntity::class],
    version = 1
)

abstract class ApodDatabase : RoomDatabase() {
    abstract val apodDao: ApodDao
}