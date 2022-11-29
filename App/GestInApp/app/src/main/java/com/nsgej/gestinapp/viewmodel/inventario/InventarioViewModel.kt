package com.nsgej.gestinapp.viewmodel.inventario

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nsgej.gestinapp.data.dataclass.InventarioDC
import com.nsgej.gestinapp.data.repository.InventarioRepositorio
import com.nsgej.gestinapp.domain.model.Inventario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventarioViewModel  @Inject constructor(private val objRepo: InventarioRepositorio) : ViewModel(){

    fun RegistraInventario(inventario: Inventario){
        viewModelScope.launch {
            objRepo.insertInventario(inventario)
            Log.i("L-inventario", "inventario Registrado")
        }
    }
    fun actualizarInventario(inventario: Inventario){
        viewModelScope.launch { objRepo.updateInventario(inventario) }
    }
    fun eliminarInventario(cod: String){
        viewModelScope.launch { objRepo.deleteInventario(cod) }
    }

    val listarTodoInventario: LiveData<List<Inventario>> = objRepo.listarTodoinventario().asLiveData()

    private var _codigoObtenido = MutableLiveData<Int>()
    val codigoObtenido:LiveData<Int> = _codigoObtenido

    //métodos para Firebase

    val listarTodoInventarioFirebase: MutableList<InventarioDC> = objRepo.listarTodoinventarioFirebase()


    fun RegistraInventarioFireabase(inventario: InventarioDC){
        viewModelScope.launch {

            objRepo.insertInventarioFirebase(inventario)
            Log.i("L-inventario", "inventario Registrado")
        }
    }


    fun nuevoregistroAgregado() {

        Log.i("L-Nuevo Reg:", "registro agregado recientemente")
        viewModelScope.launch {
            val inventarioDC = objRepo.listarUltimoInventionFirebase();
            if (inventarioDC != null) {
                _codigoObtenido.value = inventarioDC.codigo +1
            }else{
                _codigoObtenido.value = 0;
            }

        }

    }


    /*val retornarcodUltimoRegistro= liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
        val codig = objInvDC.getCodigoInventario()
            emit(codig)
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }*/


}