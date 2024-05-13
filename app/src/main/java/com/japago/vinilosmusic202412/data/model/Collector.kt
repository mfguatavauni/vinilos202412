package com.japago.vinilosmusic202412.data.model

data class Collector (
    val collectorId: Int,
    val name:String,
    val telephone:String,
    val email:String,
    val comments: List<Any>,
    val favoritePerformers: List<Any>,
    val collectorAlbums: List<Any>,
)