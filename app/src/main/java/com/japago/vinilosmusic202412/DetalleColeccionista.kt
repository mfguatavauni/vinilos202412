package com.japago.vinilosmusic202412

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.japago.vinilosmusic202412.adapters.CollectorItemAdapter
import com.japago.vinilosmusic202412.data.model.Album
import com.japago.vinilosmusic202412.databinding.DetailCollectorBinding
import com.japago.vinilosmusic202412.viewmodels.DetailCollectorViewModel

class DetalleColeccionista: AppCompatActivity() {

    private lateinit var binding: DetailCollectorBinding
    private lateinit var detailCollectorViewModel: DetailCollectorViewModel //by viewModels()
    private var viewModelAdapter: CollectorItemAdapter? = null
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_collector)
        viewModelAdapter = CollectorItemAdapter()
        recyclerView = findViewById(R.id.albumes_collector_rv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = viewModelAdapter

        binding = DetailCollectorBinding.inflate(layoutInflater)

        val collectorName = findViewById<TextView>(R.id.txtName)
        val collectorPhone = findViewById<TextView>(R.id.txtPhone)
        val collectorEmai = findViewById<TextView>(R.id.txtEmail)

        val collectorId:Int = intent.getStringExtra("id_collector")!!.toInt()
        collectorName.text = intent.getStringExtra("name_collector")
        collectorPhone.text = intent.getStringExtra("phone_collector")
        collectorEmai.text = intent.getStringExtra("email_collector")

        // Observar cambios en coleccionistas y actualizar la interfaz de usuario
        val activity = requireNotNull(this) {
            "You can only access the viewModel after onActivityCreated()"
        }

        detailCollectorViewModel = ViewModelProvider(this, DetailCollectorViewModel.Factory(this.application,collectorId)).get(DetailCollectorViewModel::class.java)
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

        val btnRegresar: Button = findViewById(R.id.btnRegresarCatalogoColeccionistas)

        btnRegresar.setOnClickListener {
            val intent = Intent(this, CatalogoColeccionistas::class.java)
            startActivity(intent)
        }
    }

}