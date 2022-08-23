package com.bkarakoca.ui_nasa_apod.apodlisting

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
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
    lateinit var adapterApodListLatest: AdapterApodList

    @Inject
    lateinit var adapterApodListFavorites: AdapterApodList

    override fun initialize() {
        if (!isViewInitialized) {
            viewModel.initVM()
        }

        if (isViewInitialized) {
            viewModel.fetchLocalApodList()
        }
        binder.fragmentApodListRecyclerview.adapter = ConcatAdapter(
            adapterApodListFavorites,
            adapterApodListLatest
        )
    }

    override fun setListeners() {
        adapterApodListLatest.setOnClickListener {
            viewModel.onApodItemClicked(it)
        }

        adapterApodListFavorites.setOnClickListener {
            viewModel.onApodItemClicked(it)
        }

        binder.fragmentApodListReorder.setOnClickListener {
            viewModel.onReorderClicked()
        }
    }

    override fun setReceivers() {
        observe(viewModel.apodListUIModel) {
            adapterApodListLatest.submitList(it.apodListLatest)
            adapterApodListFavorites.submitList(it.apodListFavorites)
        }
    }
}