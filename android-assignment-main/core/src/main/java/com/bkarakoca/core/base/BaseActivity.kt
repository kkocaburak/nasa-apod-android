package com.bkarakoca.core.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {
    @get:LayoutRes
    abstract val layoutId: Int

    protected abstract val viewModel: VM

    abstract fun showLoading()
    abstract fun hideLoading()

    val binder by lazy<B>(LazyThreadSafetyMode.NONE) {
        DataBindingUtil.setContentView(this, layoutId)
    }
}