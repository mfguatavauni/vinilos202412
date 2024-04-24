package viewmodels
import androidx.lifecycle.ViewModel

class ContadorViewModel : ViewModel() {
    // Variables y métodos para manejar datos para la UI
    var contador: Int = 0

    fun incrementarContador() {
        contador++
    }
}