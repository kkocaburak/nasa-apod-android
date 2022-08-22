package com.bkarakoca.domain.uimodel

import com.bkarakoca.core.base.ListAdapterItem

data class ApodListUIModel(
    val apodList: List<ApodUIModel>
)

data class ApodUIModel(
    val title: String,
    val explanation: String,
    val date: String,
    val imageUrl: String?,
) : ListAdapterItem