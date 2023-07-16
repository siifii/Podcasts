package com.siifii.thamanyah.domain.playlist.repository

import com.siifii.thamanyah.domain.playlist.model.PlaylistResponse


interface PlaylistRepository {
    suspend fun getPlaylist(): PlaylistResponse
}