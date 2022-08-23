package com.bkarakoca.domain.uimodel.apod

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ApodListUIModel(
    var apodListLatest: ArrayList<ApodListModel>,
    var apodListFavorites: ArrayList<ApodListModel>
)

fun ApodListUIModel.sortByTitleAsc(): ApodListUIModel {
    return this.copy(
        apodListLatest = ArrayList(apodListLatest.sortedBy {
            it.title
        }),
        apodListFavorites = ArrayList(apodListFavorites.sortedBy {
            it.title
        })
    )
}

fun ApodListUIModel.sortByDateDes(): ApodListUIModel {
    return this.copy(
        apodListLatest = ArrayList(apodListLatest.sortedByDescending {
            it.date
        }),
        apodListFavorites = ArrayList(apodListFavorites.sortedByDescending {
            it.date
        })
    )
}

@Parcelize
data class ApodUIModel(
    override val id: Long? = 0,
    override val title: String,
    val explanation: String,
    override val date: String,
    val imageUrl: String?,
    var isFavorite: Boolean
) : Parcelable, ApodListModel

fun ApodUIModel.reverseFavorite() {
    isFavorite = !isFavorite
}