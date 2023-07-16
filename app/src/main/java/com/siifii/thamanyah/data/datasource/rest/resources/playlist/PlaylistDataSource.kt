package com.siifii.thamanyah.data.datasource.rest.resources.playlist

import com.siifii.thamanyah.data.datasource.rest.response.Result
import com.siifii.thamanyah.data.dto.playlist.PlaylistResponseDto

interface PlaylistDataSource {
    suspend fun getPlaylist(): Result<PlaylistResponseDto>
}