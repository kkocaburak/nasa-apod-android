package com.bkarakoca.ui_nasa_apod.apodlisting

import com.bkarakoca.core.base.BaseBindingAdapter
import com.bkarakoca.core.base.BaseBindingViewHolder
import com.bkarakoca.domain.uimodel.apod.ApodHeaderModel
import com.bkarakoca.domain.uimodel.apod.ApodListModel
import com.bkarakoca.domain.uimodel.apod.ApodUIModel
import com.bkarakoca.ui_nasa_apod.R
import com.bkarakoca.ui_nasa_apod.databinding.ItemApodBinding
import javax.inject.Inject

class AdapterApodList @Inject constructor() : BaseBindingAdapter<ApodListModel>() {

    private var onApodClickListener: ((ApodUIModel) -> Unit)? = null

    override fun getItemLayoutId(position: Int): Int {
        return when (getItem(position)) {
            is ApodUIModel -> R.layout.item_apod
            is ApodHeaderModel -> R.layout.item_apod_header
            else -> -1
        }
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder<ApodListModel>, position: Int) {
        when (holder.binding) {
            is ItemApodBinding -> {
                val binding = holder.binding as ItemApodBinding
                binding.itemApodContainer.setOnClickListener {
                    onApodClickListener?.invoke(getItem(position) as ApodUIModel)
                }
            }
        }

        super.onBindViewHolder(holder, position)
    }

    fun setOnClickListener(listener: ((ApodUIModel) -> Unit)) {
        onApodClickListener = listener
    }
}