package com.bkarakoca.data

import com.bkarakoca.data.model.AstronomyPicture
import retrofit2.http.GET

interface PlanetaryService {
    /**
     * APOD - Astronomy Picture of the day.
     * See [the docs](https://api.nasa.gov/) and [github micro service](https://github.com/nasa/apod-api#docs-)
     */
    @GET("planetary/apod?count=20")
    suspend fun getPictures(): List<AstronomyPicture>

}
