package com.japago.vinilosmusic202412

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.japago.vinilosmusic202412.data.model.Album
import com.japago.vinilosmusic202412.databinding.ActivityCrearAlbumBinding
import com.japago.vinilosmusic202412.viewmodels.AlbumViewModel
import data.VolleyBroker
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date
import java.util.Locale


class CrearAlbum : AppCompatActivity() {
    private lateinit var binding: ActivityCrearAlbumBinding

    private val albumViewModel: AlbumViewModel by viewModels()



    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrearAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        albumViewModel.albumModel.observe(this) { currentAlbum ->
            showToast("Album creado correctamente " + currentAlbum.id)
            Handler(Looper.getMainLooper()).postDelayed({
                //Retornar a la pantalla principal
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }, 2000)
        }


        //Botones
        val btnRetornar = findViewById<Button>(R.id.cmdRetornar)
        val btnCrearAlbum = findViewById<Button>(R.id.submitButton)

        //Controles
        val txtGenred = findViewById<MaterialAutoCompleteTextView>(R.id.idGenre)
        val txtRecord = findViewById<MaterialAutoCompleteTextView>(R.id.idRecord)
        val txtDateRelease = findViewById<TextInputEditText>(R.id.idReleaseDate)


        //Cargar las opciones
        val items = listOf("Classical", "Salsa", "Rock", "Folk")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, items)
        txtGenred.setAdapter(adapter)

        val itemsDisc = listOf("Sony Music", "EMI", "Discos Fuentes", "Elektra", "Fania Records")
        val adapterD = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, itemsDisc)
        txtRecord.setAdapter(adapterD)

        //Mostrar el calendario para seleccioanr la fecha de lanzamiento
        txtDateRelease.setOnClickListener{
            showDatePickerDialog()
        }

        btnCrearAlbum.setOnClickListener{
            val (valida, mensaje) = validateInputs(binding.idName.text.toString(), binding.idCover.text.toString(), binding.idReleaseDate.text.toString(), binding.idGenre.text.toString(), binding.idRecord.text.toString(), binding.idDesc.text.toString())

            if (valida){
                val parser = SimpleDateFormat("dd/MM/yyyy")
                val formatter = SimpleDateFormat("MM.dd.yyyy")

                val album = Album(
                    id = 0,
                    name = binding.idName.text.toString(),
                    cover = binding.idCover.text.toString(),
                    releaseDate = formatter.format(parser.parse(binding.idReleaseDate.text.toString())),
                    description = binding.idDesc.text.toString(),
                    genre = binding.idGenre.text.toString(),
                    recordLabel = binding.idRecord.text.toString()
                )

                albumViewModel.createAlbum(album)

            }else{
                showToast("Validar:\n$mensaje")
            }
        }

        btnRetornar.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
        }

    }


    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Selecciona una fecha")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        datePickerDialog.addOnPositiveButtonClickListener {
            // Convertir el tiempo en milisegundos a formato de fecha y actualizar el EditText
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            //editTextDate.setText(dateFormat.format(Date(it)))
            //txtDateRelease.setText(dateFormat.format(Date(it)))
            val txtDateRelease = findViewById<TextInputEditText>(R.id.idReleaseDate)
            txtDateRelease.setText(dateFormat.format(Date(it)))
        }

        datePickerDialog.show(supportFragmentManager, datePickerDialog.toString())
    }

    private fun showToast(message: String) {
        //Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)  // Alinea el Toast al centro de la pantalla
        toast.show()
    }

    private fun validateInputs(name: String, cover: String, release: String, genre: String, record: String, description: String): Pair<Boolean, String>{
        var mensaje: String = ""
        var retorno: Boolean = true

        if (name.isEmpty()){
            mensaje += "• ${getString(R.string.nameAlbum)} \n"
            retorno = false
        }

        if (cover.isEmpty()){
            mensaje += "• ${getString(R.string.nameUrl)} \n"
            retorno = false
        }

        if (release.isEmpty()){
            mensaje += "• ${getString(R.string.dateRelease)} \n"
            retorno = false
        }

        if (genre.isEmpty()){
            mensaje += "• ${getString(R.string.inputGenre)} \n"
            retorno = false
        }

        if (record.isEmpty()){
            mensaje += "• ${getString(R.string.inputRecord)} \n"
            retorno = false
        }

        if (description.isEmpty()){
            mensaje += "• ${getString(R.string.inputDescription)} \n"
            retorno = false
        }

        return Pair(retorno, mensaje)
    }

}