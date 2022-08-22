package com.bkarakoca.core.navigation

import androidx.fragment.app.DialogFragment
import androidx.navigation.NavDirections
import com.bkarakoca.core.popup.PopupListener
import com.bkarakoca.core.popup.PopupModel

sealed class NavigationCommand {
    data class ToDirection(val directions: NavDirections) : NavigationCommand()
    data class ToDialog(val dialogFragment: DialogFragment, val tag: String) :
        NavigationCommand()
    data class Popup(val model: PopupModel, val listener: PopupListener? = null) :
        NavigationCommand()

    object Back : NavigationCommand()
}
