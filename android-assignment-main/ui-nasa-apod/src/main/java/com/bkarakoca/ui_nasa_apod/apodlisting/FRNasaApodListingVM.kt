package com.bkarakoca.ui_nasa_apod.apodlisting

import androidx.lifecycle.MutableLiveData
import com.bkarakoca.core.base.BaseViewModel
import com.bkarakoca.core.extension.launch
import com.bkarakoca.core.extension.notifyDataChange
import com.bkarakoca.domain.uimodel.ApodListUIModel
import com.bkarakoca.domain.uimodel.ApodUIModel
import com.bkarakoca.domain.uimodel.sortByDateDes
import com.bkarakoca.domain.uimodel.sortByTitleAsc
import com.bkarakoca.domain.usecase.GetNasaApodListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class FRNasaApodListingVM @Inject constructor(
    private val getNasaApodListUseCase: GetNasaApodListUseCase
) : BaseViewModel() {

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

    fun onApodItemClicked(apodUIModel: ApodUIModel) {
        navigate(FRNasaApodListingDirections.toFRNasaApodDetail(apodUIModel))
    }

    fun onReorderClicked() {
        val reorderDialog = DialogFRApodListReorder { isByTitle, isByDate ->
            orderApodList(isByTitle, isByDate)
        }

        navigate(reorderDialog, FRNasaApodListingVM::class.java.toString())
    }


    private fun orderApodList(byTitle: Boolean, byDate: Boolean) {
        when {
            byTitle -> apodListUIModel.value?.sortByTitleAsc()
            byDate -> apodListUIModel.value?.sortByDateDes()
        }

        apodListUIModel.notifyDataChange()
    }

}