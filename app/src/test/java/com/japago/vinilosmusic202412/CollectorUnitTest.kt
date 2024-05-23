package com.japago.vinilosmusic202412

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.japago.vinilosmusic202412.data.model.Collector
import com.japago.vinilosmusic202412.network.NetworkServiceAdapter
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Test
import org.junit.Assert.*
class CollectorUnitTest (){
    @Test
    fun getCollector_isCorrect() {
        val application:Application = Application()
        val onError: ((VolleyError) -> Unit)? = null
        onError?.let {
            NetworkServiceAdapter.getInstance(application).getCollector(1,{
                //Guardar los coleccionistas de la variable it en un almacén de datos local para uso futuro
                println("Response $it")
                assertThat(it,instanceOf(Collector::class.java))
            },
                it
            )
        }
    }
}