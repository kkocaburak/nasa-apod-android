package com.bkarakoca.ui_nasa_apod.apoddetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.bkarakoca.core.base.BaseViewModel
import com.bkarakoca.core.extension.launch
import com.bkarakoca.core.extension.postDataChange
import com.bkarakoca.domain.uimodel.apod.ApodUIModel
import com.bkarakoca.domain.uimodel.apod.reverseFavorite
import com.bkarakoca.domain.usecase.HandleApodFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FRNasaApodDetailVM @Inject constructor(
    private val handle: SavedStateHandle,
    private val handleApodFavoriteUseCase: HandleApodFavoriteUseCase
) : BaseViewModel() {

    companion object {
        const val APOD_UI_MODEL = "apodUIModel"
    }

    val apodDetailUIModel = MutableLiveData<ApodUIModel>(handle[APOD_UI_MODEL])

    fun onFavoriteClicked() = launch {
        withContext(Dispatchers.IO) {
            apodDetailUIModel.value?.let {
                handleApodFavoriteUseCase(it)
                    .onStart { showLoading() }
                    .collect {
                        hideLoading()
                        apodDetailUIModel.value?.reverseFavorite()
                        apodDetailUIModel.postDataChange()
                    }
            }
        }
    }

}