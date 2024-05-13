package com.japago.vinilosmusic202412.data

import com.japago.vinilosmusic202412.data.model.AlbumesItem
import com.japago.vinilosmusic202412.data.model.Collector
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("albums")
    suspend fun listCatalogoAlbumes(
    ): List<AlbumesItem>

    @GET("collectors")
    suspend fun listCollectors(
    ): List<Collector>

    @GET("albums/{id}")
    suspend fun getAlbumDetails(@Path("id") id: String): AlbumesItem
}

object RetrofitServiceFactory{
    fun makeRetrofitService(): RetrofitService{
        return Retrofit.Builder()
            .baseUrl("http://190.109.6.4:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }
}