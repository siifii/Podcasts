package com.siifii.thamanyah.ui.home

import androidx.lifecycle.viewModelScope
import com.siifii.thamanyah.core.util.SingleLiveEvent
import com.siifii.thamanyah.domain.playlist.model.PlaylistResponse
import com.siifii.thamanyah.domain.playlist.usecase.PlaylistUseCase
import com.siifii.thamanyah.presentation.base.BaseViewModel
import com.siifii.thamanyah.presentation.base.IBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: PlaylistUseCase) : BaseViewModel() {
    private val playlistLiveData = SingleLiveEvent<PlaylistResponse>()


    fun getPlaylist(
    ) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            startProgress()
            useCase()
            playlistLiveData.postValue(useCase.invoke())
            statusData.postValue(
                IBaseViewModel.StatusData(
                    IBaseViewModel.Status.SUCCESS,
                    useCase.invoke()
                )
            )
            stopProgress()
        }
    }

}