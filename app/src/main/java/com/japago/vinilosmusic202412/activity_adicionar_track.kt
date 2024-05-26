package com.japago.vinilosmusic202412

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.japago.vinilosmusic202412.data.model.Track
import com.japago.vinilosmusic202412.databinding.ActivityAdicionarTrackBinding
import com.japago.vinilosmusic202412.viewmodels.TrackViewModel

class activity_adicionar_track : AppCompatActivity() {
    private lateinit var binding: ActivityAdicionarTrackBinding
    private val TrackViewModel: TrackViewModel by viewModels()
    private val MessageCreate = "@string/msgCreateSuccess"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdicionarTrackBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_adicionar_track)

        TrackViewModel.trackModel.observe(this) { currentTrack ->
            showToast(MessageCreate)

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, DetalleAlbumActivity::class.java)
                intent.putExtra("id_album", currentTrack.albumId.toInt())
                startActivity(intent)
            }, 2000)

        }



        val idAlbum = intent.getIntExtra("id_album", -1)
        val albumName = intent.getStringExtra("album_name")

        val btnRegresar: Button = findViewById(R.id.btnRegresarDetalleAlbumes)
        val btnCrearTrack: Button = findViewById(R.id.submitButton)

        val txtAlbumName = findViewById<TextView>(R.id.idAlbumName)
        txtAlbumName.text = albumName

        btnRegresar.setOnClickListener {
            val intent = Intent(this, DetalleAlbumActivity::class.java)
            intent.putExtra("id_album", idAlbum)
            startActivity(intent)
        }

        btnCrearTrack.setOnClickListener {
            val trackName = findViewById<TextView>(R.id.idSongName)
            val trackDuration = findViewById<TextView>(R.id.idSongDuration)

            val (valida, message) = validateInputs(trackName.text.toString(), trackDuration.text.toString())
            if (valida){
                val trackSong = Track(
                    id = "",
                    albumId = idAlbum.toString(),
                    name = trackName.text.toString(),
                    duration = trackDuration.text.toString()
                )

                val track = TrackViewModel.createTrack(trackSong)
            }else{
                showToast("Por favor ingrese los siguientes datos: \n$message")
            }
        }
    }


    private fun showToast(message: String) {
        //Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)  // Alinea el Toast al centro de la pantalla
        toast.show()
    }


    private fun validateInputs(nameSong: String, duration: String ): Pair<Boolean, String> {
        var mensaje: String = ""
        var retorno: Boolean = true


        if (nameSong.isEmpty()) {
            mensaje += "• ${getString(R.string.inputSongName)} \n"
            retorno = false
        }

        if (duration.isEmpty()) {
            mensaje += "• ${getString(R.string.inputDuration)} \n"
            retorno = false
        }

        return Pair(retorno, mensaje)
    }
}