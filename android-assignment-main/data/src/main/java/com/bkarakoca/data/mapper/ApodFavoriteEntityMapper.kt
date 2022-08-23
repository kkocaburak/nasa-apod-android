package com.bkarakoca.data.mapper

import com.bkarakoca.data.model.ApodFavoriteEntity
import com.bkarakoca.domain.uimodel.apod.ApodUIModel
import javax.inject.Inject

class ApodFavoriteEntityMapper @Inject constructor() {

    fun map(model: ApodUIModel): ApodFavoriteEntity =
        ApodFavoriteEntity(
            name = model.title
        )

}