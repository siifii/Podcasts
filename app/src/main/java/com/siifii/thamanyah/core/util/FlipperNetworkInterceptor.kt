package com.siifii.thamanyah.core.util

import com.facebook.flipper.plugins.network.NetworkFlipperPlugin

object FlipperNetworkInterceptor {

    private var interceptor: NetworkFlipperPlugin? = null

    fun getFlipperNetworkPlugin(): NetworkFlipperPlugin {
        if (interceptor == null) {
            interceptor = NetworkFlipperPlugin()
        }
        return interceptor!!
    }
}