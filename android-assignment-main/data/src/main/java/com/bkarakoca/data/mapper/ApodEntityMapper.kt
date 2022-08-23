package com.bkarakoca.data.mapper

import com.bkarakoca.data.model.ApodEntity
import com.bkarakoca.data.model.ApodResponseModel
import javax.inject.Inject

class ApodEntityMapper @Inject constructor() {

    fun map(responseList: List<ApodResponseModel>): List<ApodEntity> = responseList.map {
        with (it) {
            ApodEntity(
                title = title,
                explanation = explanation,
                date = date,
                hdUrl = hdUrl
            )
        }
    }

}