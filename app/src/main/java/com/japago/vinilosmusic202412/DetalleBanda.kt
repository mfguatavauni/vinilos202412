package com.japago.vinilosmusic202412

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.japago.vinilosmusic202412.adapters.BandsDetailAdapter
import com.japago.vinilosmusic202412.databinding.BandItemBinding
import com.japago.vinilosmusic202412.viewmodels.BandViewModel

class DetalleBanda : AppCompatActivity() {
    private lateinit var binding: BandItemBinding
    private val bandViewModel: BandViewModel by viewModels()
    private var viewModelAdapter: BandsDetailAdapter? = null
    private lateinit var recyclerView: RecyclerView
}