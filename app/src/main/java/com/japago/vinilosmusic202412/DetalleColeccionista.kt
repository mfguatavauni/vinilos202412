package com.japago.vinilosmusic202412

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.japago.vinilosmusic202412.adapters.CollectorsAdapter
import com.japago.vinilosmusic202412.data.model.Album
import com.japago.vinilosmusic202412.data.model.Collector
import com.japago.vinilosmusic202412.databinding.DetailCollectorBinding
import com.japago.vinilosmusic202412.viewmodels.DetailCollectorViewModel

class DetalleColeccionista: AppCompatActivity() {

    private lateinit var binding: DetailCollectorBinding
    private val detailCollectorViewModel: DetailCollectorViewModel by viewModels()
    private var viewModelAdapter: CollectorsAdapter? = null
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_collector)
        viewModelAdapter = CollectorsAdapter()
        recyclerView = findViewById(R.id.albumes_collector_rv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = viewModelAdapter

        binding = DetailCollectorBinding.inflate(layoutInflater)
        //setContentView(binding.root)

        // Observar cambios en coleccionistas y actualizar la interfaz de usuario
        detailCollectorViewModel.albumes_collector.observe(this, Observer<List<Album>>{ it ->
            it.apply {
                viewModelAdapter!!.albumes = this
            }
        })

        enableEdgeToEdge()
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        /*val btnRegresar: Button = findViewById(R.id.btnRegresarCatalogoAlbumes)

        btnRegresar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }*/
    }

}