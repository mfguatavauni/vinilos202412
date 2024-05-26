package com.japago.vinilosmusic202412.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.japago.vinilosmusic202412.data.model.Track
import com.japago.vinilosmusic202412.network.NetworkServiceAdapter

class TrackViewModel(applicacion: Application): AndroidViewModel(applicacion){
    val trackModel = MutableLiveData<Track>()

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    fun createTrack(track: Track) {
        var sTrack: Track



        NetworkServiceAdapter.getInstance(getApplication()).addTrack(track,{
            sTrack = Track(albumId = it.getString("albumId"), id = it.getString("id"),name = it.getString("name"), duration = it.getString("duration"))
            trackModel.postValue(sTrack)

            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        })
    }

}