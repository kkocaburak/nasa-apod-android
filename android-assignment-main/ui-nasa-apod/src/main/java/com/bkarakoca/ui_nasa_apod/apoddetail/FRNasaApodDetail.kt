package com.bkarakoca.ui_nasa_apod.apoddetail

import androidx.fragment.app.viewModels
import com.bkarakoca.core.base.BaseFragment
import com.bkarakoca.ui_nasa_apod.R
import com.bkarakoca.ui_nasa_apod.databinding.FragmentNasaApodDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FRNasaApodDetail : BaseFragment<FRNasaApodDetailVM, FragmentNasaApodDetailBinding>() {

    override val layoutId: Int = R.layout.fragment_nasa_apod_detail

    override val viewModel: FRNasaApodDetailVM by viewModels()

    override fun initialize() {}

    override fun setListeners() {
        binder.fragmentApodDetailImageFavorite.setOnClickListener {
            viewModel.onFavoriteClicked()
        }
    }

    override fun setReceivers() {}
}