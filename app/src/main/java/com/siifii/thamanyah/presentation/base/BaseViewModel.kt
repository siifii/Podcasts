package com.siifii.thamanyah.presentation.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.siifii.thamanyah.core.util.SingleLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

abstract class BaseViewModel : IBaseViewModel,
    LifecycleObserver, ViewModel() {

    final override val statusData = MutableLiveData<IBaseViewModel.StatusData<*>>()
    final override val progressData = MutableLiveData<IBaseViewModel.Progress>()
    final override val errorData = SingleLiveEvent<Throwable>()
    final override val errorResourceData = SingleLiveEvent<Int>()
    val navDirections: SingleLiveEvent<NavDirections> = SingleLiveEvent()


    init {
        progressData.postValue(IBaseViewModel.Progress.IDDLE)
    }

    override fun startProgress() {
        progressData.postValue(IBaseViewModel.Progress.PROCESSING)
    }

    override fun stopProgress() {
        progressData.postValue(IBaseViewModel.Progress.IDDLE)
    }


    open val handler = CoroutineExceptionHandler { _, exception ->
        GlobalScope.launch(Dispatchers.Main) {
            errorData.postValue(exception)
            if (progressData.value == IBaseViewModel.Progress.PROCESSING)
                progressData.postValue(IBaseViewModel.Progress.IDDLE)
        }
    }
}
