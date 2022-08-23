package com.bkarakoca.domain.uimodel.apod

data class ApodHeaderModel(
    override val id: Long?,
    val headerText: String,
    override val title: String = "!",
    override val date: String = "~",
) : ApodListModel