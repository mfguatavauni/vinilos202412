package com.japago.vinilosmusic202412

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.japago.vinilosmusic202412.adapters.AlbumAdapter
import com.japago.vinilosmusic202412.data.RetrofitServiceFactory
import kotlinx.coroutines.launch

class CatalogoAlbumes : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo_albumes)

        recyclerView = findViewById(R.id.recyclerViewAlbumes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val service = RetrofitServiceFactory.makeRetrofitService()

        lifecycleScope.launch {
            val albumes = service.listCatalogoAlbumes()
            adapter = AlbumAdapter(albumes)
            recyclerView.adapter = adapter
        }

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
