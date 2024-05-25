package com.japago.vinilosmusic202412.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.japago.vinilosmusic202412.data.model.Band
import com.japago.vinilosmusic202412.data.model.Collector
import com.japago.vinilosmusic202412.network.NetworkServiceAdapter

class BandsRepository (val application: Application){

    fun getBandDetail(bandId: Int, callback: (List<Band>)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getBandById(bandId,{
            callback(it)
        },
            onError
        )
    }
}