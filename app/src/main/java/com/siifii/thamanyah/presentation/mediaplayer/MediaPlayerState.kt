package com.siifii.thamanyah.presentation.mediaplayer

sealed class MediaPlayerState {
    object Loading : MediaPlayerState()
    object Paused : MediaPlayerState()
    object Started : MediaPlayerState()
    object Stopped : MediaPlayerState()
}
