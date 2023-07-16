package com.siifii.thamanyah.core.mapper.playlist

import com.siifii.thamanyah.data.dto.playlist.EpisodesItemDTO
import com.siifii.thamanyah.data.dto.playlist.PlaylistDTO
import com.siifii.thamanyah.data.dto.playlist.PlaylistDataDTO
import com.siifii.thamanyah.data.dto.playlist.PlaylistResponseDto
import com.siifii.thamanyah.domain.playlist.model.EpisodesItem
import com.siifii.thamanyah.domain.playlist.model.Playlist
import com.siifii.thamanyah.domain.playlist.model.PlaylistData
import com.siifii.thamanyah.domain.playlist.model.PlaylistResponse

class PlaylistResponseMapperImpl : PlaylistResponseMapper {

    override fun mapFromDto(inputData: PlaylistResponseDto): PlaylistResponse {
        val data = inputData.data?.let { mapPlaylistDataFromDto(it) }
        return PlaylistResponse(data!!)
    }

    override fun mapToDto(inputData: PlaylistResponse): PlaylistResponseDto? {
        val data = mapPlaylistDataToDto(inputData.data)
        return PlaylistResponseDto(data)
    }

    private fun mapPlaylistDataFromDto(dto: PlaylistDataDTO): PlaylistData {
        val playlist = dto.playlist.let { mapPlaylistFromDto(it) }
        val episodes = dto.episodes.map { mapEpisodeItemFromDto(it) }
        return PlaylistData(playlist, episodes)
    }

    private fun mapPlaylistDataToDto(data: PlaylistData): PlaylistDataDTO {
        val playlist = data.playlist?.let { mapPlaylistToDto(it) }
        val episodes = data.episodes.map { mapEpisodeItemToDto(it) }
        return PlaylistDataDTO(playlist!!, episodes)
    }

    private fun mapPlaylistFromDto(dto: PlaylistDTO): Playlist {
        return Playlist(
            dto.image,
            dto.access,
            dto.episodeCount,
            dto.episodeTotalDuration,
            dto.description,
            dto.type,
            dto.followingCount,
            dto.userId,
            dto.createdAt,
            dto.isSubscribed,
            dto.isDeleted,
            dto.name,
            dto.id,
            dto.status,
            dto.updatedAt
        )
    }

    private fun mapPlaylistToDto(playlist: Playlist): PlaylistDTO {
        return PlaylistDTO(
            playlist.image,
            playlist.access,
            playlist.episodeCount,
            playlist.episodeTotalDuration,
            playlist.description,
            playlist.type,
            playlist.followingCount,
            playlist.userId,
            playlist.createdAt,
            playlist.isSubscribed,
            playlist.isDeleted,
            playlist.name,
            playlist.id,
            playlist.status,
            playlist.updatedAt
        )
    }

    private fun mapEpisodeItemFromDto(dto: EpisodesItemDTO): EpisodesItem {
        return EpisodesItem(
            dto.image,
            dto.audioLink,
            dto.durationInSeconds,
            dto.releaseDate,
            dto.description,
            dto.imageBigger,
            dto.sentNotification,
            dto.type,
            dto.podcastItunesId,
            dto.duration,
            dto.createdAt,
            dto.isDeleted,
            dto.name,
            dto.podcastName,
            dto.id,
            dto.position,
            dto.isEditorPick,
            dto.views,
            dto.itunesId,
            dto.updatedAt
        )
    }

    private fun mapEpisodeItemToDto(item: EpisodesItem): EpisodesItemDTO {
        return EpisodesItemDTO(
            item.image,
            item.audioLink,
            item.durationInSeconds,
            item.releaseDate,
            item.description,
            item.imageBigger,
            item.sentNotification,
            item.type,
            item.podcastItunesId,
            item.duration,
            item.createdAt,
            item.isDeleted,
            item.name,
            item.podcastName,
            item.id,
            item.position,
            item.isEditorPick,
            item.views,
            item.itunesId,
            item.updatedAt
        )
    }
}
