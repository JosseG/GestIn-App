package com.nsgej.gestinapp.viewmodel.inventario

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nsgej.gestinapp.data.entities.InventarioEntity
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
}