package com.bkarakoca.ui_nasa_apod.apodlisting

import com.bkarakoca.core.base.BaseBindingAdapter
import com.bkarakoca.domain.uimodel.ApodUIModel
import com.bkarakoca.ui_nasa_apod.R
import javax.inject.Inject

class AdapterApodList @Inject constructor() : BaseBindingAdapter<ApodUIModel>() {
    override fun getItemLayoutId(): Int = R.layout.item_apod
}