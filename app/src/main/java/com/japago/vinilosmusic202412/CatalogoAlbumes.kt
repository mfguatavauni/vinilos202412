package com.japago.vinilosmusic202412

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.japago.vinilosmusic202412.data.RetrofitServiceFactory
import kotlinx.coroutines.launch

class CatalogoAlbumes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val service = RetrofitServiceFactory.makeRetrofitService()

        lifecycleScope.launch {
            val albumes = service.listCatalogoAlbumes()
        }

        enableEdgeToEdge()
        setContentView(R.layout.activity_catalogo_albumes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}