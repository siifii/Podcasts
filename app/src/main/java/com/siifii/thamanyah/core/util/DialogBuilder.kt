package com.siifii.thamanyah.core.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.siifii.thamanyah.R

object DialogBuilder {
    private var basicCustomDialog: Dialog? = null


    fun buildCustomDialog(
        mContext: Context,
        title: String = mContext.getString(R.string.error),
        description: String?
    ): Dialog {
        if (basicCustomDialog != null) {
            return basicCustomDialog!!
        }

        basicCustomDialog =
            Dialog(mContext, R.style.DialogTheme)
        basicCustomDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        basicCustomDialog?.setCancelable(true)
        basicCustomDialog?.setContentView(R.layout.layout_basic_dialog)
        basicCustomDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val titleTV: AppCompatTextView = basicCustomDialog?.findViewById(R.id.titleTV)!!
        val descriptionTV: AppCompatTextView =
            basicCustomDialog?.findViewById(R.id.descriptionTV)!!
        val okayBtn: AppCompatButton = basicCustomDialog?.findViewById(R.id.okBtn)!!

        titleTV.text = title
        descriptionTV.text = description ?: mContext.getString(R.string.un_expected_error)

        okayBtn.setOnClickListener {
            basicCustomDialog?.dismiss()
            basicCustomDialog = null
        }

        return basicCustomDialog!!

    }
}