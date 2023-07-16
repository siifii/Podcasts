package com.siifii.thamanyah.data.datasource.rest.interceptors

import com.siifii.thamanyah.core.util.Constants
import com.siifii.thamanyah.data.datasource.resources.preferences.PreferencesDataSource
import okhttp3.Request

class AuthInterceptor(
    private val preferencesDataSource: PreferencesDataSource
) : RequestInterceptor() {

    override fun addSpecificRequestParams(builder: Request.Builder) {
        preferencesDataSource.getStringFromPreferences(
            Constants.SharedPreference.ACCESS_TOKEN
        )?.let {
            builder.addHeader(
                "authorization",
                "Bearer $it"
            )
                .addHeader("Connection", "close")

        }

    }

    override fun getSpecificPath() = ""

}