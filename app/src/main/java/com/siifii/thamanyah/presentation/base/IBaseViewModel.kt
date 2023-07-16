package com.siifii.thamanyah.presentation.base

import androidx.lifecycle.LiveData

interface IBaseViewModel {

    enum class Status {
        SUCCESS, ERROR
    }

    enum class Progress {
        PROCESSING, IDDLE,
    }

    data class StatusData<T>(val status: Status, val payload: T)

    val statusData: LiveData<StatusData<*>>
    val progressData: LiveData<Progress>
    val errorData: LiveData<Throwable>
    val errorResourceData: LiveData<Int>

    fun startProgress()

    fun stopProgress()

}