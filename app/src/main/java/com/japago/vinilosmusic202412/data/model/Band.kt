package com.japago.vinilosmusic202412.data.model

data class Band (
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val creationDate: String,
    val albums: List<Any>,
    val musicians: List<Any>,
    val performerPrizes: List<Any>
)