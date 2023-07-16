package com.siifii.thamanyah.core.mapper


interface Mapper<T : Any, V : Any> {

    fun mapFromDto(inputData: T): V? {
        return null
    }

    fun mapToDto(inputData: V): T? {
        return null
    }
}
