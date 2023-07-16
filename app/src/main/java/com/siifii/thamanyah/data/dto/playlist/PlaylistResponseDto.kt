package com.siifii.thamanyah.data.dto.playlist

import com.google.gson.annotations.SerializedName

data class PlaylistResponseDto(
    @field:SerializedName("data") val data: PlaylistDataDTO? = null
)

data class PlaylistDataDTO(
    @field:SerializedName("playlist") val playlist: PlaylistDTO = PlaylistDTO(),
    @field:SerializedName("episodes") val episodes: List<EpisodesItemDTO> = emptyList()
)

data class PlaylistDTO(
    @field:SerializedName("image") val image: String? = null,
    @field:SerializedName("access") val access: String? = null,
    @field:SerializedName("episodeCount") val episodeCount: Int? = null,
    @field:SerializedName("episodeTotalDuration") val episodeTotalDuration: Int? = null,
    @field:SerializedName("description") val description: String? = null,
    @field:SerializedName("type") val type: Int? = null,
    @field:SerializedName("followingCount") val followingCount: Int? = null,
    @field:SerializedName("userId") val userId: String? = null,
    @field:SerializedName("createdAt") val createdAt: String? = null,
    @field:SerializedName("isSubscribed") val isSubscribed: Boolean? = null,
    @field:SerializedName("isDeleted") val isDeleted: Boolean? = null,
    @field:SerializedName("name") val name: String? = null,
    @field:SerializedName("id") val id: String? = null,
    @field:SerializedName("status") val status: String? = null,
    @field:SerializedName("updatedAt") val updatedAt: String? = null
)

data class EpisodesItemDTO(
    @field:SerializedName("image") val image: String? = null,
    @field:SerializedName("audioLink") val audioLink: String? = null,
    @field:SerializedName("durationInSeconds") val durationInSeconds: Int? = null,
    @field:SerializedName("releaseDate") val releaseDate: String? = null,
    @field:SerializedName("description") val description: String? = null,
    @field:SerializedName("imageBigger") val imageBigger: String? = null,
    @field:SerializedName("sentNotification") val sentNotification: Boolean? = null,
    @field:SerializedName("type") val type: Int? = null,
    @field:SerializedName("podcastItunesId") val podcastItunesId: String? = null,
    @field:SerializedName("duration") val duration: Int? = null,
    @field:SerializedName("createdAt") val createdAt: String? = null,
    @field:SerializedName("isDeleted") val isDeleted: Boolean? = null,
    @field:SerializedName("name") val name: String? = null,
    @field:SerializedName("podcastName") val podcastName: String? = null,
    @field:SerializedName("id") val id: String? = null,
    @field:SerializedName("position") val position: Int? = null,
    @field:SerializedName("isEditorPick") val isEditorPick: Boolean? = null,
    @field:SerializedName("views") val views: Int? = null,
    @field:SerializedName("itunesId") val itunesId: String? = null,
    @field:SerializedName("updatedAt") val updatedAt: String? = null
)
