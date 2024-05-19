package com.japago.vinilosmusic202412.repositories
import android.app.Application
import com.android.volley.VolleyError
import com.japago.vinilosmusic202412.data.model.Album
import com.japago.vinilosmusic202412.data.model.Collector
import com.japago.vinilosmusic202412.network.NetworkServiceAdapter

class CollectorsRepository (val application: Application){
    fun refreshData(callback: (List<Collector>)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getCollectors({
            //Guardar los coleccionistas de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }

    fun getAlbumesCollector(collectorId: Int, callback: (List<Album>)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getAlbumesCollector(collectorId,{
            //Guardar el Colleccionista de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }
}