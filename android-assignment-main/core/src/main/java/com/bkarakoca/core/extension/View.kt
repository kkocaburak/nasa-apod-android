package com.bkarakoca.core.extension

import android.view.View

val View.isVisible get() = visibility == View.VISIBLE

fun View.show() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}
