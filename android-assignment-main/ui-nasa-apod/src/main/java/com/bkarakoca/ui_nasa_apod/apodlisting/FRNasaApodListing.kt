package com.bkarakoca.ui_nasa_apod.apodlisting

import androidx.fragment.app.viewModels
import com.bkarakoca.core.base.BaseFragment
import com.bkarakoca.core.extension.observe
import com.bkarakoca.ui_nasa_apod.R
import com.bkarakoca.ui_nasa_apod.databinding.FragmentNasaApodListingBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FRNasaApodListing : BaseFragment<FRNasaApodListingVM, FragmentNasaApodListingBinding>() {

    override val layoutId: Int = R.layout.fragment_nasa_apod_listing

    override val viewModel: FRNasaApodListingVM by viewModels()

    @Inject
    lateinit var adapterApodList: AdapterApodList

    override fun initialize() {
        viewModel.initVM()

        binder.repoListingRecyclerview.adapter = adapterApodList
    }

    override fun setListeners() {}

    override fun setReceivers() {
        observe(viewModel.apodListUIModel) {
            adapterApodList.submitList(it.apodList)
        }
    }
}