package com.bkarakoca.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apodEntity")
data class ApodEntity(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val title: String,
    val explanation: String,
    val date: String,
    val hdUrl: String?,
    var isFavorite: Boolean? = false
)