package com.japago.vinilosmusic202412.data.model

data class AlbumesItem(
    val comments: List<Any>,
    val cover: String,
    val description: String,
    val genre: String,
    val id: Int,
    val name: String,
    val performers: List<Any>,
    val recordLabel: String,
    val releaseDate: String,
    val tracks: List<Any>
)