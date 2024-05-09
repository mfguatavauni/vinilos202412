package com.japago.vinilosmusic202412

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Observer
import com.japago.vinilosmusic202412.adapters.BandsAdapter
import com.japago.vinilosmusic202412.data.model.Band
import com.japago.vinilosmusic202412.databinding.BandItemBinding
import com.japago.vinilosmusic202412.viewmodels.BandViewModel


class CatalogoBandas : AppCompatActivity() {
    private lateinit var binding: BandItemBinding
    private val bandViewModel: BandViewModel by viewModels()
    private var viewModelAdapter: BandsAdapter? = null
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo_bandas)
        viewModelAdapter = BandsAdapter()
        recyclerView = findViewById(R.id.band_item_rv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = viewModelAdapter

        binding = BandItemBinding.inflate(layoutInflater)

        bandViewModel.bands.observe(this, Observer<List<Band>>{ it ->
            it.apply {
                viewModelAdapter!!.bands = this
            }
        })

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnRegresar: Button = findViewById(R.id.btnRegresarCatalogoAlbumes)

        btnRegresar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}