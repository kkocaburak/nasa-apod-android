package com.bkarakoca.ui_nasa_apod.apodlisting

import androidx.lifecycle.MutableLiveData
import com.bkarakoca.core.base.BaseViewModel
import com.bkarakoca.core.extension.launch
import com.bkarakoca.domain.uimodel.ApodListUIModel
import com.bkarakoca.domain.usecase.GetNasaApodListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class FRNasaApodListingVM @Inject constructor(
    private val getNasaApodListUseCase: GetNasaApodListUseCase
): BaseViewModel() {

    val apodListUIModel = MutableLiveData<ApodListUIModel>()

    fun initVM() {
        fetchNasaApodList()
    }

    private fun fetchNasaApodList() = launch {
        getNasaApodListUseCase()
            .onStart {
                // you can show loading
            }
            .onCompletion {
                // you can hide loading
            }
            .collect {
                apodListUIModel.value = it
            }
    }

}