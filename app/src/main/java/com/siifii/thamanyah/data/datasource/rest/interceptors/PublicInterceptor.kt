package com.siifii.thamanyah.data.datasource.rest.interceptors

import android.content.Context
import okhttp3.Request

class PublicInterceptor(private val context: Context) : RequestInterceptor() {
    override fun addSpecificRequestParams(builder: Request.Builder) {
        builder.addHeader("x-locale", "en")
            .header("Connection", "close")
    }

    override fun getSpecificPath() = ""
}