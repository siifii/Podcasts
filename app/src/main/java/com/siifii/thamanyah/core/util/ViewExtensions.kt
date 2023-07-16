package com.siifii.thamanyah.core.util

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.enableDisable(enabled: Boolean) {
    this.isEnabled = enabled
    if (this is ViewGroup) {
        val group = this
        for (idx in 0 until group.childCount) {
            group.getChildAt(idx).enableDisable(enabled)
        }
    }
}

fun ImageView.loadCircleImageUrl(url: String?) {
    this.load(url) {
        crossfade(true)
        transformations(CircleCropTransformation())
    }
}
