package com.japago.vinilosmusic202412

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.japago.vinilosmusic202412.data.RetrofitServiceFactory
import kotlinx.coroutines.launch

class DetalleAlbumActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_album)

        val idAlbum = intent.getIntExtra("id_album", -1)

        if (idAlbum == -1) {
            // Maneja el caso en que no se proporcionó un idAlbum
            finish()
            return
        }

        val service = RetrofitServiceFactory.makeRetrofitService()

        lifecycleScope.launch {
            try {
                val album = service.getAlbumDetails(idAlbum.toString())

                // Actualiza la interfaz de usuario con los detalles del álbum
                val albumCover = findViewById<ImageView>(R.id.albumCover)
                val albumName = findViewById<TextView>(R.id.albumName)
                val albumGenre = findViewById<TextView>(R.id.albumGenre)
                val albumDescription = findViewById<TextView>(R.id.albumDescription)

                runOnUiThread {
                    Glide.with(this@DetalleAlbumActivity).load(album.cover).into(albumCover)
                    albumName.text = album.name
                    albumGenre.text = album.genre
                    albumDescription.text = album.description
                }
            } catch (e: Exception) {
                Log.e("DetalleAlbumActivity", "Error al obtener detalles del álbum", e)
            }
        }

        val btnRegresar: Button = findViewById(R.id.btnRegresarDetalleAlbumes)
        val btnAdicionarTrack: Button = findViewById(R.id.btnAddTrack)

        btnRegresar.setOnClickListener {
            val intent = Intent(this, CatalogoAlbumes::class.java)
            startActivity(intent)
        }


        btnAdicionarTrack.setOnClickListener {
            val albumName =  findViewById<TextView>(R.id.albumName)
            val intent = Intent(this, activity_adicionar_track::class.java)
            intent.putExtra("id_album", idAlbum)
            intent.putExtra("album_name", albumName.text)
            startActivity(intent)
        }
    }
}