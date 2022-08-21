package com.bkarakoca.data.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class AstronomyPicture(
    @SerializedName("service_version")
    val serviceVersion: String,
    val title: String,
    val explanation: String,
    val date: LocalDate,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("hdurl")
    val hdUrl: String?,
    val url: String,
)