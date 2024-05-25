package com.japago.vinilosmusic202412

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class DetalleBanda : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_banda)

        val bandName = findViewById<TextView>(R.id.tvName)
        val bandimage = findViewById<ImageView>(R.id.band_image)
        val bandDate = findViewById<TextView>(R.id.tvDate)
        val bandDescription = findViewById<TextView>(R.id.tvDescription)

        val bandId:Int = intent.getStringExtra("id_band")!!.toInt()
        val pathImage:String = intent.getStringExtra("image_band")!!

        bandName.text = intent.getStringExtra("name_band")
        bandDate.text = intent.getStringExtra("date_band")
        bandDescription.text = intent.getStringExtra("description_band")

        Glide.with(this@DetalleBanda).load(pathImage).into(bandimage)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.band_detail)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnRegresar: Button = findViewById(R.id.btnRegresarCatalogoBandas)

        btnRegresar.setOnClickListener {
            val intent = Intent(this, CatalogoBandas::class.java)
            startActivity(intent)
        }
    }

}