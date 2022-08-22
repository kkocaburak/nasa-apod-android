package com.adyen.android.assignment.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.adyen.android.assignment.R
import com.adyen.android.assignment.databinding.ActivityMainBinding
import com.bkarakoca.core.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ACMain : BaseActivity<ActivityMainBinding, ACMainVM>() {

    override val layoutId get() = R.layout.activity_main

    override val viewModel: ACMainVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binder.lifecycleOwner = this
    }
}
