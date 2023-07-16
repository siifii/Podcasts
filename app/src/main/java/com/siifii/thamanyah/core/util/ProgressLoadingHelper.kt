package com.siifii.thamanyah.core.util

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.siifii.thamanyah.R
import com.siifii.thamanyah.presentation.base.IBaseViewModel

class ProgressLoadingHelper {

    private var dialogLoading: AlertDialog? = null

    fun showDialog(context: Context) {
        dialogLoading =
            MaterialAlertDialogBuilder(ContextThemeWrapper(context, R.style.AlertDialog))
                .setCancelable(false)
                .setView(R.layout.dialog_loading)
                .create()
                .apply {
                    setCanceledOnTouchOutside(false)
                    show()
                }
    }

    fun showProgress(
        context: Context,
        viewModel: IBaseViewModel,
        viewLifecycleOwner: LifecycleOwner
    ) {
        showProgress(context, viewModel.progressData, viewLifecycleOwner)
    }

    fun showProgress(
        context: Context,
        progressData: LiveData<IBaseViewModel.Progress>,
        viewLifecycleOwner: LifecycleOwner
    ) {
        progressData.observe(
            viewLifecycleOwner
        ) {
            if (it == IBaseViewModel.Progress.IDDLE) {
                hideDialog()

            } else {
                hideDialog()
                showDialog(context)
            }
        }
    }

    fun hideDialog() {
        if (dialogLoading != null && dialogLoading!!.isShowing) {
            dialogLoading!!.dismiss()
            dialogLoading = null
        }
    }

    fun setColor(context: Context, progressBar: ProgressBar, colorId: Int) {
        val colorFilter =
            PorterDuffColorFilter(
                ContextCompat.getColor(context, colorId),
                PorterDuff.Mode.MULTIPLY
            )
        progressBar.indeterminateDrawable.colorFilter = colorFilter
    }

    companion object {

        private var mLoadingProgressHelper: ProgressLoadingHelper? = null
        fun getInstance(): ProgressLoadingHelper {
            if (mLoadingProgressHelper == null) {
                mLoadingProgressHelper =
                    ProgressLoadingHelper()
            }

            return mLoadingProgressHelper!!
        }
    }
}