package com.bkarakoca.data.mapper

import com.bkarakoca.data.model.ApodEntity
import com.bkarakoca.domain.uimodel.apod.ApodListModel
import com.bkarakoca.domain.uimodel.apod.ApodListUIModel
import com.bkarakoca.domain.uimodel.apod.ApodUIModel
import javax.inject.Inject

class ApodMapper @Inject constructor() {

    fun map(response: List<ApodEntity>) = ApodListUIModel(
        apodListLatest = response.filter {
            it.isFavorite == false
        }.map {
            with(it) {
                ApodUIModel(
                    id = id,
                    title = title,
                    explanation = explanation,
                    date = date,
                    imageUrl = hdUrl,
                    isFavorite = isFavorite ?: false
                )
            }
        } as ArrayList<ApodListModel>,

        apodListFavorites = response.filter {
            it.isFavorite == true
        }.map {
            with(it) {
                ApodUIModel(
                    id = id,
                    title = title,
                    explanation = explanation,
                    date = date,
                    imageUrl = hdUrl,
                    isFavorite = isFavorite ?: false
                )
            }
        } as ArrayList<ApodListModel>
    )

}