package com.japago.vinilosmusic202412.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.japago.vinilosmusic202412.data.model.Album
import com.japago.vinilosmusic202412.repositories.CollectorsRepository

class DetailCollectorViewModel(application: Application) :  AndroidViewModel(application) {

    private val _albumes_collector = MutableLiveData<List<Album>>()
    private val collectorsRepository = CollectorsRepository(application)

    val albumes_collector: LiveData<List<Album>>
        get() = _albumes_collector

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        getDataCollectorFromNetwork(1)
    }

    private fun getDataCollectorFromNetwork(collectorId:Int) {
        collectorsRepository.getAlbumesCollector(collectorId,{
            _albumes_collector.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        })
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailCollectorViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetailCollectorViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}

