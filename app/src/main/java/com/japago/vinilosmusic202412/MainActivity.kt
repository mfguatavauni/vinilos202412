package com.japago.vinilosmusic202412

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val cvArtistas: CardView = findViewById(R.id.cvArtistas)

        cvArtistas.setOnClickListener {
            val intent = Intent(this, CatalogoBandas::class.java)
            startActivity(intent)
        }

        val cvAlbumes: CardView = findViewById(R.id.cvAlbumes)

        cvAlbumes.setOnClickListener {
            val intent = Intent(this, CatalogoAlbumes::class.java)
            startActivity(intent)
        }

        val cvColeccionistas: CardView = findViewById(R.id.cvColeccionistas)

        cvColeccionistas.setOnClickListener {
            val intent = Intent(this, CatalogoColeccionistas::class.java)
            startActivity(intent)
        }

        val btnCrearAlbum = findViewById<Button>(R.id.btnCrearAlbum)

        btnCrearAlbum.setOnClickListener {
            val intent = Intent(this, CrearAlbum::class.java)
            startActivity(intent)
        }

    }

}