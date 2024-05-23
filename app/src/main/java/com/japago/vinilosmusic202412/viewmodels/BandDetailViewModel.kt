package com.japago.vinilosmusic202412.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.japago.vinilosmusic202412.data.model.Band
import com.japago.vinilosmusic202412.repositories.BandsRepository

class BandDetailViewModel(application: Application, bandId: Int) : AndroidViewModel(application) {
    private val _band = MutableLiveData<Band>()
    private val bandsRepository = BandsRepository(application)

    val band: LiveData<Band>
        get() = _band

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    val id:Int = bandId

    init {
        getDataFromNetwork()
    }

    private fun getDataFromNetwork(){
        bandsRepository.getBandDetail(id,{
            _band.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        })
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application, val bandId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BandDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return BandDetailViewModel(app,bandId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}