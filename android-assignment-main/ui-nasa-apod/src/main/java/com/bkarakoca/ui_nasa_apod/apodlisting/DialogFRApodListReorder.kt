package com.bkarakoca.ui_nasa_apod.apodlisting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.fragment.app.DialogFragment
import com.bkarakoca.ui_nasa_apod.R

class DialogFRApodListReorder(
    private val applyClickListener: (isOrderByTitle: Boolean, isOrderByDate: Boolean) -> Unit
) : DialogFragment() {

    companion object {
        const val WIDTH_MULTIPLIER = 0.85
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        return inflater.inflate(R.layout.dialog_apod_reorder, container, false)
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * WIDTH_MULTIPLIER).toInt()
        dialog?.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)

        setListeners()
    }

    private fun setListeners() {
        dialog?.window?.apply {
            val checkboxTitle = findViewById<CheckBox>(R.id.dialog_apod_checkbox_reorder_by_title)
            val checkboxDate = findViewById<CheckBox>(R.id.dialog_apod_checkbox_reorder_by_date)

            findViewById<Button>(R.id.dialog_apod_button_apply)?.setOnClickListener {
                val isByTitle = checkboxTitle.isChecked
                val isByDate = checkboxDate.isChecked
                applyClickListener.invoke(isByTitle, isByDate)
                dismiss()
            }

            checkboxTitle.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    checkboxDate.isChecked = false
                }
            }

            checkboxDate.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    checkboxTitle.isChecked = false
                }
            }
        }
    }

}