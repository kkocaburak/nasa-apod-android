package com.bkarakoca.data.mapper

import com.bkarakoca.data.model.ApodResponseModel
import com.bkarakoca.domain.uimodel.ApodListUIModel
import com.bkarakoca.domain.uimodel.ApodUIModel
import javax.inject.Inject

class ApodPicturesMapper @Inject constructor() {

    fun map(response: List<ApodResponseModel>) = ApodListUIModel(
        response.map {
            with(it) {
                ApodUIModel(
                    title = title,
                    explanation = explanation,
                    date = date,
                    imageUrl = hdUrl
                )
            }
        }
    )

}