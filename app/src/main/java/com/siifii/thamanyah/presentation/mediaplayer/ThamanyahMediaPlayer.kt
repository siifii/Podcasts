package com.siifii.thamanyah.presentation.mediaplayer

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.URL

class ThamanyahMediaPlayer private constructor() {
    private var mediaPlayer: MediaPlayer? = null
    private var audioFile: File? = null
    private var currentState: MediaPlayerState = MediaPlayerState.Stopped
    private val stateLiveData = MutableLiveData<MediaPlayerState>()

    companion object {
        private var instance: ThamanyahMediaPlayer? = null

        fun getInstance(): ThamanyahMediaPlayer {
            if (instance == null) {
                instance = ThamanyahMediaPlayer()
            }
            return instance!!
        }
    }

    fun getCurrentState(): LiveData<MediaPlayerState> {
        return stateLiveData
    }

    suspend fun playAudio(url: String) {
        currentState = MediaPlayerState.Loading
        stateLiveData.value = currentState

        withContext(Dispatchers.IO) {
            try {
                mediaPlayer?.release()
                mediaPlayer = MediaPlayer().apply {
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .build()
                    )
                    setDataSource(url)
                    prepareAsync()
                    setOnPreparedListener {
                        start()
                        currentState = MediaPlayerState.Started
                        stateLiveData.value = currentState
                    }
                    setOnCompletionListener {
                        currentState = MediaPlayerState.Stopped
                        stateLiveData.value = currentState
                        downloadAudio(url)
                    }
                    setOnErrorListener { _, what, extra ->
                        currentState = MediaPlayerState.Stopped
                        stateLiveData.value = currentState
                        true
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                currentState = MediaPlayerState.Stopped
                stateLiveData.value = currentState
            }
        }
    }

    private fun downloadAudio(url: String) {
        val file = File.createTempFile("audio", ".mp3")

        try {
            currentState = MediaPlayerState.Loading
            stateLiveData.value = currentState

            val inputStream = URL(url).openStream()
            val outputStream = FileOutputStream(file)

            val buffer = ByteArray(1024)
            var bytesRead: Int
            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                outputStream.write(buffer, 0, bytesRead)
            }

            inputStream.close()
            outputStream.close()

            audioFile = file
            currentState = MediaPlayerState.Stopped
            stateLiveData.value = currentState
        } catch (e: Exception) {
            // Handle any errors that occur during download
            e.printStackTrace()
            currentState = MediaPlayerState.Stopped
            stateLiveData.value = currentState
        }
    }

    suspend fun playAudioList(urls: List<String>, isShuffle: Boolean) {
        currentState = MediaPlayerState.Loading
        stateLiveData.value = currentState

        mediaPlayer?.release()
        mediaPlayer = null

        if (isShuffle) {
            urls.shuffled()
        } else {
            urls
        }

        val scope = CoroutineScope(Dispatchers.IO)

        urls.forEach { url ->
            scope.launch {
                playAudio(url)
            }
        }
    }

    fun stopAudio() {
        mediaPlayer?.apply {
            if (isPlaying) {
                stop()
            }
            release()
        }
        mediaPlayer = null
        currentState = MediaPlayerState.Stopped
        stateLiveData.value = currentState
    }

    fun pauseAudio() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
            currentState = MediaPlayerState.Paused
            stateLiveData.value = currentState
        }
    }

    fun resumeAudio() {
        if (mediaPlayer?.isPlaying == false) {
            mediaPlayer?.start()
            currentState = MediaPlayerState.Started
            stateLiveData.value = currentState
        }
    }
}
