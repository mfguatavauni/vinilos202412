package com.japago.vinilosmusic202412

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Response
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import data.VolleyBroker
import viewmodels.ContadorViewModel
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class CrearAlbum : AppCompatActivity() {
    lateinit var volleyBroker: VolleyBroker
    private val contadorViewModel: ContadorViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crear_album)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Consumo de API
        volleyBroker = VolleyBroker(this.applicationContext)

        //Botones
        val btnRetornar = findViewById<Button>(R.id.cmdRetornar)
        val btnCrearAlbum = findViewById<Button>(R.id.submitButton)

        //Controles
        val txtName = findViewById<TextInputEditText>(R.id.idName)
        val txtUrlCover = findViewById<TextInputEditText>(R.id.idCover)
        val txtDateRelease = findViewById<TextInputEditText>(R.id.idReleaseDate)
        val txtGenred = findViewById<MaterialAutoCompleteTextView>(R.id.idGenre)
        val txtRecord = findViewById<MaterialAutoCompleteTextView>(R.id.idRecord)
        val txtMulti = findViewById<TextInputEditText>(R.id.idDesc)


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
            val (valida, mensaje) = validateInputs(txtName, txtUrlCover, txtDateRelease, txtGenred, txtRecord, txtMulti)

            if (valida){
                showToast("Registrado correctamente")
            }else{
                showToast("Validar:\n$mensaje")
            }


            //Invocar el servicio
            if (2 ==3){
                volleyBroker.instance.add(VolleyBroker.getRequest("collectors",
                    { response ->
                        // Display the first 500 characters of the response string.
                        txtMulti.setText("Response is: ${response}")
                    },
                    {
                        //Log.d("TAG", it.toString())
                        txtMulti.setText("That didn't work!" + it.toString())
                    }
                ))
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

    private fun validateInputs(name: TextInputEditText, cover: TextInputEditText, release: TextInputEditText, genre: MaterialAutoCompleteTextView, record: MaterialAutoCompleteTextView, description: TextInputEditText): Pair<Boolean, String>{
        var mensaje: String = ""
        var retorno: Boolean = true

        if (name.text.toString().isEmpty()){
            mensaje += "• ${getString(R.string.nameAlbum)} \n"
            retorno = false;
        }

        if (cover.text.toString().isEmpty()){
            mensaje += "• ${getString(R.string.nameUrl)} \n"
            retorno = false;
        }

        if (release.text.toString().isEmpty()){
            mensaje += "• ${getString(R.string.dateRelease)} \n"
            retorno = false;
        }

        if (genre.text.toString().isEmpty()){
            mensaje += "• ${getString(R.string.inputGenre)} \n"
            retorno = false;
        }

        if (record.text.toString().isEmpty()){
            mensaje += "• ${getString(R.string.inputRecord)} \n"
            retorno = false;
        }

        if (description.text.toString().isEmpty()){
            mensaje += "• ${getString(R.string.inputDescription)} \n"
            retorno = false;
        }

        return Pair(retorno, mensaje)
    }

}