package com.japago.vinilosmusic202412.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.japago.vinilosmusic202412.data.model.Album
import com.japago.vinilosmusic202412.network.NetworkServiceAdapter

class AlbumViewModel(applicacion: Application): AndroidViewModel(applicacion) {

    val albumModel = MutableLiveData<Album>()

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    fun createAlbum(album: Album) {
        var sAlbum: Album

        NetworkServiceAdapter.getInstance(getApplication()).createAlbum(album,{
            sAlbum = Album(id = it.getInt("id"),name = it.getString("name"), cover = it.getString("cover"), releaseDate = it.getString("releaseDate"), description = it.getString("description"), genre = it.getString("genre"), recordLabel = it.getString("recordLabel"))
            albumModel.postValue(sAlbum)

            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        })
    }


}