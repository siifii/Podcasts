package com.siifii.thamanyah.data.datasource.rest.resources.playlist

import com.siifii.thamanyah.data.datasource.rest.PublicApiFunctions
import com.siifii.thamanyah.data.datasource.rest.response.Result
import com.siifii.thamanyah.data.datasource.rest.response.safeApiResult
import com.siifii.thamanyah.data.dto.playlist.PlaylistResponseDto
import javax.inject.Inject

class PlaylistDataSourceImpl @Inject constructor(private val api: PublicApiFunctions) :
    PlaylistDataSource {

    override suspend fun getPlaylist(): Result<PlaylistResponseDto> {
        return safeApiResult(
            call = {
                api.getPlaylist()
            }, place = "get playlist API"
        )
    }

}