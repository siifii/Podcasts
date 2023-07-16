package com.siifii.thamanyah.data.datasource.rest.resources.playlist

import com.siifii.thamanyah.BuildConfig
import com.siifii.thamanyah.data.dto.playlist.PlaylistResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface PlaylistApi {

    @GET("api/playlist/01GVD0TTY5RRMHH6YMCW7N1H70")
    suspend fun getPlaylist(
        @Header("Authorization") authorization: String = BuildConfig.AUTH
    ): Response<PlaylistResponseDto>

}