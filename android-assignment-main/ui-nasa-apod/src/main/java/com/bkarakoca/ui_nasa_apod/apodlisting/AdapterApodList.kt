package com.bkarakoca.ui_nasa_apod.apodlisting

import com.bkarakoca.core.base.BaseBindingAdapter
import com.bkarakoca.core.base.BaseBindingViewHolder
import com.bkarakoca.domain.uimodel.ApodUIModel
import com.bkarakoca.ui_nasa_apod.R
import com.bkarakoca.ui_nasa_apod.databinding.ItemApodBinding
import javax.inject.Inject

class AdapterApodList @Inject constructor() : BaseBindingAdapter<ApodUIModel>() {
    override fun getItemLayoutId(): Int = R.layout.item_apod

    private var onApodClickListener: ((ApodUIModel) -> Unit)? = null

    override fun onBindViewHolder(holder: BaseBindingViewHolder<ApodUIModel>, position: Int) {
        (holder.binding as ItemApodBinding).itemApodContainer.setOnClickListener {
            onApodClickListener?.invoke(getItem(position))
        }
        super.onBindViewHolder(holder, position)
    }

    fun setOnClickListener(listener: ((ApodUIModel) -> Unit)) {
        onApodClickListener = listener
    }
}