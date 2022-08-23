package com.bkarakoca.domain.uimodel.apod

import com.bkarakoca.core.base.ListAdapterItem

interface ApodListModel : ListAdapterItem {
    val title: String
    val date: String
}