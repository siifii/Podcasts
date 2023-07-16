package com.siifii.thamanyah.presentation.base

import androidx.fragment.app.Fragment
import com.siifii.thamanyah.core.util.ProgressLoadingHelper

open class BaseFragment : Fragment() {

    protected fun showLoading() {
        this.context?.let {
            ProgressLoadingHelper.getInstance().showDialog(it)
        }
    }

    protected fun bindLoading(viewModel: IBaseViewModel) {
        this.context?.let {
            ProgressLoadingHelper.getInstance()
                .showProgress(it, viewModel = viewModel, viewLifecycleOwner = this)
        }
    }

    protected fun hideLoading() {
        ProgressLoadingHelper.getInstance().hideDialog()
    }

    override fun onStop() {
        super.onStop()
        hideLoading()
    }

}