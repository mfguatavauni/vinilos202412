package com.japago.vinilosmusic202412

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.japago.vinilosmusic202412.adapters.CollectorsAdapter
import com.japago.vinilosmusic202412.databinding.CollectorItemBinding
import com.japago.vinilosmusic202412.data.model.Collector
import com.japago.vinilosmusic202412.viewmodels.CollectorViewModel

class CatalogoColeccionistas : AppCompatActivity() {

    private lateinit var binding: CollectorItemBinding
    private val collectorViewModel: CollectorViewModel by viewModels()
    private var viewModelAdapter: CollectorsAdapter? = null
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo_coleccionistas)
        viewModelAdapter = CollectorsAdapter()
        recyclerView = findViewById(R.id.collector_item_rv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = viewModelAdapter

        binding = CollectorItemBinding.inflate(layoutInflater)
        //setContentView(binding.root)

        // Observar cambios en coleccionistas y actualizar la interfaz de usuario
        collectorViewModel.collectors.observe(this, Observer<List<Collector>>{ it ->
            it.apply {
                viewModelAdapter!!.collectors = this
            }
        })

        /*enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
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