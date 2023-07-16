package com.siifii.thamanyah.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.siifii.thamanyah.databinding.ListItemsPodcastBinding
import com.siifii.thamanyah.domain.playlist.model.EpisodesItem

class PlaylistAdapter :
    PagingDataAdapter<EpisodesItem, PlaylistAdapter.PlaylistHolder>(
        DIFF_CALLBACK
    ) {

    private lateinit var episodesItem: List<EpisodesItem>

    val onPlayAudioClickedMTL: MutableLiveData<Pair<Int, EpisodesItem>> = MutableLiveData()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistHolder =
        PlaylistHolder(
            ListItemsPodcastBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = if (::episodesItem.isInitialized) episodesItem.size else 0

    override fun onBindViewHolder(holder: PlaylistHolder, position: Int) {
        holder.bind(episodesItem[position], position)
    }

    fun addEpisodesList(episodesItem: List<EpisodesItem>) {
        this.episodesItem = episodesItem
        notifyDataSetChanged()
    }


    inner class PlaylistHolder(private val mBinding: ListItemsPodcastBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        fun bind(
            episodesItem: EpisodesItem,
            position: Int
        ) {
            mBinding.item = episodesItem
            mBinding.ivPodcastPlay.setOnClickListener {
                onPlayAudioClickedMTL.value = position to episodesItem
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<EpisodesItem>() {
            override fun areItemsTheSame(oldItem: EpisodesItem, newItem: EpisodesItem) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: EpisodesItem, newItem: EpisodesItem) =
                oldItem == newItem
        }
    }
}