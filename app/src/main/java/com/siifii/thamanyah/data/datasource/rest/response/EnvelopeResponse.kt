package com.siifii.thamanyah.data.datasource.rest.response

import com.google.gson.annotations.SerializedName

data class EnvelopeResponse<T>(
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: T
)