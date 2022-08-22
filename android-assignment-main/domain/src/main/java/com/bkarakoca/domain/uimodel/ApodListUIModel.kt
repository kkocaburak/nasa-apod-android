package com.bkarakoca.domain.uimodel

import android.os.Parcelable
import com.bkarakoca.core.base.ListAdapterItem
import kotlinx.parcelize.Parcelize

data class ApodListUIModel(
    val apodList: List<ApodUIModel>
)

@Parcelize
data class ApodUIModel(
    val title: String,
    val explanation: String,
    val date: String,
    val imageUrl: String?,
) : ListAdapterItem, Parcelable