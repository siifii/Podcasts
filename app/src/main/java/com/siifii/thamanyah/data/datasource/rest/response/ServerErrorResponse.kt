package com.siifii.thamanyah.data.datasource.rest.response

import com.google.gson.annotations.SerializedName

data class ServerErrorResponse(
    @SerializedName("message")
    val message: String
)