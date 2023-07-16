package com.siifii.thamanyah.data.datasource.rest.interceptors

import com.siifii.thamanyah.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

abstract class RequestInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()

        val request: Request = original.newBuilder()
            .method(original.method, original.body)
            .apply {
                addSpecificRequestParams(this)
            }
            .build()

        return chain.proceed(addPrefixes(request))
    }

    private fun addPrefixes(request: Request): Request {
        val requestBuilder = request.newBuilder()

        val httpUrl = request.url
        val httpUrlBuilder = httpUrl.newBuilder()

        val prefixIndex = httpUrl.pathSegments.indexOf("{api_prefix}")
        if (prefixIndex >= 0) {
            httpUrlBuilder.setPathSegment(
                prefixIndex,
                BuildConfig.APP_API_VERSION + getSpecificPath()
            )
            val decodedUrl = httpUrlBuilder.build()
            httpUrlBuilder.encodedPath(
                "/${
                    decodedUrl.pathSegments.joinToString(
                        separator = "/",
                        postfix = ""
                    )
                }"
            )
        }

        return requestBuilder.url(httpUrlBuilder.build()).build()
    }

    abstract fun addSpecificRequestParams(builder: Request.Builder)

    abstract fun getSpecificPath(): String


}