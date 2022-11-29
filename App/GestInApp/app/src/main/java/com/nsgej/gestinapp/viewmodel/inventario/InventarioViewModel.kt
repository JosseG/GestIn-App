package com.nsgej.gestinapp.viewmodel.inventario

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nsgej.gestinapp.data.dataclass.InventarioDC
import com.nsgej.gestinapp.data.repository.AlmacenRepositorio
import com.nsgej.gestinapp.data.repository.InventarioRepositorio
import com.nsgej.gestinapp.domain.model.Almacen
import com.nsgej.gestinapp.domain.model.Inventario
import com.nsgej.gestinapp.domain.model.Producto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventarioViewModel  @Inject constructor(private val objRepo: InventarioRepositorio, private val almacenRepositorio: AlmacenRepositorio,) : ViewModel(){

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

    private var _listaTodoInventarioFirebase = MutableLiveData<MutableList<InventarioDC?>>()

    val listaTodoInventarioFirebase : LiveData<MutableList<InventarioDC?>> =_listaTodoInventarioFirebase


    private var _listarProductosParaIngresar = MutableLiveData<Producto>()
    val listarProductosParaIngresar : LiveData<Producto> = _listarProductosParaIngresar

    private var _listarInventariosParaIngresar = MutableLiveData<Producto>()
    val listarInventariosParaIngresar : LiveData<Producto> = _listarInventariosParaIngresar


    private var _listaSinMiAlmacenObtenido = MutableLiveData<List<Almacen>>()

    val listaSinMiAlmacenObtenido : LiveData<List<Almacen>> = _listaSinMiAlmacenObtenido


    fun listarTodoInventarioFirebase(almacen : String){
        viewModelScope.launch {
            _listaTodoInventarioFirebase.value = objRepo.listarTodoinventarioFirebase(almacen)
        }
    }

    fun listarSinMiAlmacen(almacen : String){

        viewModelScope.launch {

            val listaSinMialmacen = mutableListOf<Almacen>()

            var almacenes = almacenRepositorio.obtenerAlmacenesSinFlow()

            almacenes.forEach {
                if (it.id != almacen){
                    listaSinMialmacen.add(it)
                }
            }

            _listaSinMiAlmacenObtenido.value = listaSinMialmacen.toList()


        }

    }




    fun obtenerListaProductosAIngresar (){



    }

    fun obtenerListaInventariosAIngresar(){
        viewModelScope.launch {

        }
    }



    fun RegistraInventarioFireabase(inventario: InventarioDC){
        viewModelScope.launch {

            objRepo.insertInventarioFirebase(inventario)
        }
    }


    fun nuevoregistroAgregado() {

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