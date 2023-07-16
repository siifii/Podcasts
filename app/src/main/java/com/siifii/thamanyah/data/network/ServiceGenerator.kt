package com.siifii.thamanyah.data.network

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.siifii.thamanyah.BuildConfig
import com.siifii.thamanyah.core.util.FlipperNetworkInterceptor
import com.siifii.thamanyah.data.datasource.rest.PublicApiFunctions
import com.siifii.thamanyah.data.datasource.rest.interceptors.AuthInterceptor
import com.siifii.thamanyah.data.datasource.rest.interceptors.PublicInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceGenerator {
    fun createService(): PublicApiFunctions {

        val httpClient = OkHttpClient.Builder()
        val loggingInterceptor =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
//        httpClient.interceptors().addAll(authInterceptors(loggingInterceptor))
        val builder = Retrofit.Builder()

        httpClient.connectTimeout(60, TimeUnit.SECONDS)
            .addNetworkInterceptor(FlipperOkhttpInterceptor(FlipperNetworkInterceptor.getFlipperNetworkPlugin()))
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            httpClient.addInterceptor(logging)
        }

        builder.baseUrl(BuildConfig.APP_URL)
            .addConverterFactory(GsonConverterFactory.create())

        val retrofit = builder.build()
        return retrofit.create(PublicApiFunctions::class.java)
    }

    private fun authInterceptors(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        publicInterceptor: PublicInterceptor
    ) = listOf(authInterceptor, publicInterceptor) + debugInterceptors(loggingInterceptor)

    private inline fun <reified T> debugInterceptors(vararg debugInterceptor: T) =
        if (BuildConfig.DEBUG) listOf(*debugInterceptor) else emptyList()
}