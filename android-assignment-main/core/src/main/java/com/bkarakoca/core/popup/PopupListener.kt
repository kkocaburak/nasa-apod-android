package com.bkarakoca.core.popup

class PopupListener(
    val onPositiveButtonClick: (() -> Unit)? = null,
    val onNegativeButtonClick: (() -> Unit)? = null
)
