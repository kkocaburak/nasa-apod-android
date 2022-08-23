package com.bkarakoca.ui_nasa_apod.apodlisting

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import com.bkarakoca.core.base.BaseViewModel
import com.bkarakoca.core.extension.launch
import com.bkarakoca.domain.uimodel.apod.ApodListUIModel
import com.bkarakoca.domain.uimodel.apod.ApodUIModel
import com.bkarakoca.domain.uimodel.apod.sortByDateDes
import com.bkarakoca.domain.uimodel.apod.sortByTitleAsc
import com.bkarakoca.domain.usecase.GetLocalNasaApodListUseCase
import com.bkarakoca.domain.usecase.GetNasaApodListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FRNasaApodListingVM @Inject constructor(
    private val getNasaApodListUseCase: GetNasaApodListUseCase,
    private val getLocalNasaApodListUseCase: GetLocalNasaApodListUseCase
) : BaseViewModel() {

    val apodListUIModel = MutableLiveData<ApodListUIModel>()

    fun initVM() {
        fetchNasaApodList()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal fun fetchNasaApodList() = launch {
        withContext(Dispatchers.IO) {
            getNasaApodListUseCase()
                .onStart { showLoading() }
                .collect {
                    hideLoading()
                    apodListUIModel.postValue(it)
                }
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


    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal fun orderApodList(byTitle: Boolean, byDate: Boolean) {
        apodListUIModel.value = when {
            byTitle -> apodListUIModel.value?.sortByTitleAsc()
            byDate -> apodListUIModel.value?.sortByDateDes()
            else -> apodListUIModel.value
        }
    }

    fun fetchLocalApodList() = launch {
        withContext(Dispatchers.IO) {
            getLocalNasaApodListUseCase()
                .onStart { showLoading() }
                .collect {
                    hideLoading()
                    apodListUIModel.postValue(it)
                }
        }
    }

}