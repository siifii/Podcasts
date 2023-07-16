package com.siifii.thamanyah.domain.playlist.model


data class PlaylistResponse(
    val data: PlaylistData
)

data class PlaylistData(
    val playlist: Playlist? = null,
    val episodes: List<EpisodesItem> = emptyList()
)

data class Playlist(
    val image: String? = null,
    val access: String? = null,
    val episodeCount: Int? = null,
    val episodeTotalDuration: Int? = null,
    val description: String? = null,
    val type: Int? = null,
    val followingCount: Int? = null,
    val userId: String? = null,
    val createdAt: String? = null,
    val isSubscribed: Boolean? = null,
    val isDeleted: Boolean? = null,
    val name: String? = null,
    val id: String? = null,
    val status: String? = null,
    val updatedAt: String? = null
)

data class EpisodesItem(
    val image: String? = null,
    val audioLink: String? = null,
    val durationInSeconds: Int? = null,
    val releaseDate: String? = null,
    val description: String? = null,
    val imageBigger: String? = null,
    val sentNotification: Boolean? = null,
    val type: Int? = null,
    val podcastItunesId: String? = null,
    val duration: Int? = null,
    val createdAt: String? = null,
    val isDeleted: Boolean? = null,
    val name: String? = null,
    val podcastName: String? = null,
    val id: String? = null,
    val position: Int? = null,
    val isEditorPick: Boolean? = null,
    val views: Int? = null,
    val itunesId: String? = null,
    val updatedAt: String? = null
)
