package com.bkarakoca.core.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bkarakoca.core.R
import com.bkarakoca.core.extension.loadImage

@BindingAdapter("imageFromUrl", "placeholderRes", "errorRes", requireAll = false)
fun setImage(
    view: ImageView,
    url: String?,
    @DrawableRes placeholderRes: Int?,
    @DrawableRes errorRes: Int?
) {

    val defaultDrawable = R.color.design_default_color_error

    view.loadImage(
        url,
        placeholderRes ?: defaultDrawable,
        errorRes ?: defaultDrawable
    )
}