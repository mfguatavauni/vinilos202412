package com.japago.vinilosmusic202412.data

import com.japago.vinilosmusic202412.data.model.AlbumesItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("albums")
    suspend fun listCatalogoAlbumes(
    ): List<AlbumesItem>

}

object RetrofitServiceFactory{
    fun makeRetrofitService(): RetrofitService{
        return Retrofit.Builder()
            .baseUrl("http://34.133.126.239:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }
}