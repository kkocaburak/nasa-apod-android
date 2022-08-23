package com.bkarakoca.core.extension

import android.content.Context
import com.bkarakoca.core.popup.Popup
import com.bkarakoca.core.popup.PopupListener
import com.bkarakoca.core.popup.PopupModel

fun Context.showPopup(model: PopupModel, listener: PopupListener? = null) {
    Popup(this, model, listener).show()
}
