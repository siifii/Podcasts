package com.siifii.thamanyah.domain.playlist.usecase

import com.siifii.thamanyah.domain.playlist.model.PlaylistResponse
import com.siifii.thamanyah.domain.playlist.repository.PlaylistRepository
import javax.inject.Inject

class PlaylistUseCase @Inject constructor(private val repository: PlaylistRepository) {

    suspend operator fun invoke(): PlaylistResponse {
        return repository.getPlaylist()
    }


}