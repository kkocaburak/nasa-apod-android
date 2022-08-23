package com.bkarakoca.data.service

import com.bkarakoca.data.model.ApodResponseModel
import retrofit2.http.GET

interface PlanetaryService {

    @GET("planetary/apod?count=20")
    suspend fun fetchApods(): List<ApodResponseModel>

}