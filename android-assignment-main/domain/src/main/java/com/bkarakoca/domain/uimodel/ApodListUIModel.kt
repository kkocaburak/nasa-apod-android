package com.bkarakoca.domain.uimodel

import android.os.Parcelable
import com.bkarakoca.core.base.ListAdapterItem
import kotlinx.parcelize.Parcelize

data class ApodListUIModel(
    var apodList: List<ApodUIModel>
)

fun ApodListUIModel.sortByTitleAsc() {
    apodList = apodList.sortedBy { it.title }
}

fun ApodListUIModel.sortByDateDes() {
    apodList = apodList.sortedByDescending { it.date }
}

@Parcelize
data class ApodUIModel(
    val title: String,
    val explanation: String,
    val date: String,
    val imageUrl: String?,
) : ListAdapterItem, Parcelable