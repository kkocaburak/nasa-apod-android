package com.bkarakoca.ui_nasa_apod.apoddetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.bkarakoca.core.base.BaseViewModel
import com.bkarakoca.domain.uimodel.ApodUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FRNasaApodDetailVM @Inject constructor(
    handle: SavedStateHandle
) : BaseViewModel() {

    companion object {
        const val APOD_UI_MODEL = "apodUIModel"
    }

    val apodDetailUIModel: LiveData<ApodUIModel> = MutableLiveData(handle[APOD_UI_MODEL])

}