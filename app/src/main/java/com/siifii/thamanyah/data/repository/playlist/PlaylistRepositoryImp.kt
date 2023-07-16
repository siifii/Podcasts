package com.siifii.thamanyah.data.repository.playlist

import com.siifii.thamanyah.core.mapper.playlist.PlaylistResponseMapper
import com.siifii.thamanyah.data.datasource.rest.resources.playlist.PlaylistDataSource
import com.siifii.thamanyah.domain.playlist.model.PlaylistResponse
import com.siifii.thamanyah.domain.playlist.repository.PlaylistRepository

class PlaylistRepositoryImp(
    private val dataSource: PlaylistDataSource,
    private val playlistResponseMapper: PlaylistResponseMapper

) : PlaylistRepository {
    override suspend fun getPlaylist(): PlaylistResponse {
        dataSource.getPlaylist().proceedResult(
            success = {
                return playlistResponseMapper.mapFromDto(it)!!
            }, error = {
                throw it
            }
        )
        throw IllegalStateException("Can't get playlist!")
    }

}
