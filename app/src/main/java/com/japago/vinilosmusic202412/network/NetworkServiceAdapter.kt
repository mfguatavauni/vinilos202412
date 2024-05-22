package com.japago.vinilosmusic202412.network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.japago.vinilosmusic202412.data.model.Album
import com.japago.vinilosmusic202412.data.model.Band
import com.japago.vinilosmusic202412.data.model.Collector
import com.japago.vinilosmusic202412.data.model.Track
import org.json.JSONArray
import org.json.JSONObject


class NetworkServiceAdapter(context: Context) {
    companion object{
        const val BASE_URL= "http://190.109.6.4:3000/"
        var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }
    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }
    fun getCollectors(onComplete:(resp:List<Collector>)->Unit, onError: (error: VolleyError)->Unit) {
        requestQueue.add(getRequest("collectors",
            { response ->
                Log.d("tag", response)
                val resp = JSONArray(response)
                val list = mutableListOf<Collector>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Collector(collectorId = item.getInt("id"),name = item.getString("name"), telephone = item.getString("telephone"), email = item.getString("email"), comments = listOf()  , favoritePerformers = listOf() , collectorAlbums = listOf()))
                }
                onComplete(list)
            },
            {
                onError(it)
            }))
    }

    fun createAlbum(album: Album, onComplete:(resp:JSONObject)->Unit, onError: (error: VolleyError)->Unit) {
        val postParams = mapOf<String, Any>(
            "name" to  album.name,
            "cover" to  album.cover,
            "releaseDate" to  album.releaseDate,
            "description" to  album.description,
            "genre" to  album.genre,
            "recordLabel" to  album.recordLabel
        )
        requestQueue.add(postRequest("albums", JSONObject(postParams),
            { response ->
                onComplete(response)
            },
            {
                onError(it)
            }))
    }

    fun addTrack(track: Track, onComplete:(resp:JSONObject)->Unit, onError: (error: VolleyError)->Unit) {
        val idAlbum = track.albumId

        val postParams = mapOf<String, Any>(
            "name" to  track.name,
            "duration" to  track.duration
        )
        requestQueue.add(postRequest("albums/" + idAlbum + "/tracks", JSONObject(postParams),
            { response ->
                onComplete(response)
            },
            {
                onError(it)
            }))
    }


    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
    }
    private fun postRequest(path: String, body: JSONObject, responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ): JsonObjectRequest {
        return  JsonObjectRequest(Request.Method.POST, BASE_URL+path, body, responseListener, errorListener)
    }
    private fun putRequest(path: String, body: JSONObject, responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ): JsonObjectRequest {
        return  JsonObjectRequest(Request.Method.PUT, BASE_URL+path, body, responseListener, errorListener)
    }

    fun getBands(onComplete:(resp:List<Band>)->Unit, onError: (error: VolleyError)->Unit){
        requestQueue.add(getRequest(path = "bands",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Band>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Band(id = item.getInt("id"),name = item.getString("name"), image = item.getString("image"), description = item.getString("description"), creationDate = item.getString("creationDate"),  albums = listOf(), musicians = listOf(), performerPrizes = listOf()))
                }
                onComplete(list)
            },
            {
                onError(it)
            }))
    }
}