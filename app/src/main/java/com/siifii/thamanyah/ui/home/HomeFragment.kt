package com.siifii.thamanyah.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.siifii.thamanyah.R
import com.siifii.thamanyah.core.util.*
import com.siifii.thamanyah.databinding.FragmentHomeBinding
import com.siifii.thamanyah.domain.playlist.model.EpisodesItem
import com.siifii.thamanyah.domain.playlist.model.PlaylistResponse
import com.siifii.thamanyah.presentation.base.BaseFragment
import com.siifii.thamanyah.presentation.mediaplayer.MediaPlayerState
import com.siifii.thamanyah.presentation.mediaplayer.ThamanyahMediaPlayer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val adapter: PlaylistAdapter = PlaylistAdapter()
    var response: PlaylistResponse? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindLoading(viewModel)

        viewModel.getPlaylist()

        binding.FBShuffle.setOnClickListener {

        }

        viewModel.statusData.observe(this) {
            response = it.payload as PlaylistResponse
            fillUIDate()
        }

        viewModel.errorData.observe(this) {
            DialogBuilder.buildCustomDialog(
                requireContext(),
                description = it.message
            ).show()
        }
    }

    private fun fillUIDate() {
        adapter.addEpisodesList(response?.data?.episodes ?: emptyList())
        binding.rvEpisodes.adapter = adapter

        binding.playlistItem = response?.data?.playlist

        adapter.onPlayAudioClickedMTL.observe(this) {
            addMediaPlayer(it.second)
        }
    }

    private fun addMediaPlayer(item: EpisodesItem) {
        binding.bottomPlayerContainer.root.visibility = View.VISIBLE

        val thamanyahMediaPlayer = ThamanyahMediaPlayer.getInstance()
        CoroutineScope(Dispatchers.Main).launch {
            thamanyahMediaPlayer.playAudio(item.audioLink ?: "")
        }

        binding.bottomPlayerContainer.tvPodcastName.text = item.name
        binding.bottomPlayerContainer.tvPodcastDescription.text = item.podcastName
        binding.bottomPlayerContainer.ivPodcast.loadCircleImageUrl(item.image)

        binding.bottomPlayerContainer.ibPlayPause.setOnClickListener {
            when (thamanyahMediaPlayer.getCurrentState().value) {
                MediaPlayerState.Started -> {
                    thamanyahMediaPlayer.pauseAudio()
                }
                MediaPlayerState.Stopped -> {
                    binding.bottomPlayerContainer.root.hide()
                }
                MediaPlayerState.Paused -> {
                    thamanyahMediaPlayer.resumeAudio()
                }
                else -> {
                    thamanyahMediaPlayer.stopAudio()
                }
            }
        }

        thamanyahMediaPlayer.getCurrentState().observe(viewLifecycleOwner) { mediaPlayerState ->
            when (mediaPlayerState) {
                MediaPlayerState.Started -> {
                    binding.bottomPlayerContainer.ibPlayPause.enableDisable(true)
                    binding.bottomPlayerContainer.ibPlayPause.setImageResource(R.drawable.ic_pause)
                    binding.bottomPlayerContainer.progressBarLoading.hide()
                }
                MediaPlayerState.Stopped -> {
                    binding.bottomPlayerContainer.ibPlayPause.setImageResource(R.drawable.ic_play)
                    binding.bottomPlayerContainer.progressBarLoading.hide()
                }
                MediaPlayerState.Loading -> {
                    binding.bottomPlayerContainer.ibPlayPause.enableDisable(false)
                    binding.bottomPlayerContainer.ibPlayPause.setImageResource(R.drawable.ic_play)
                    binding.bottomPlayerContainer.progressBarLoading.show()
                }
                else -> {
                    binding.bottomPlayerContainer.ibPlayPause.setImageResource(R.drawable.ic_play)
                    binding.bottomPlayerContainer.progressBarLoading.hide()
                }
            }
        }
    }

}

