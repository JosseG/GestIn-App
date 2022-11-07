package com.nsgej.gestinapp.viewmodel.inventario

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nsgej.gestinapp.data.entities.TipoInventarioEntity
import com.nsgej.gestinapp.data.repository.TipoInventarioRepositorio
import com.nsgej.gestinapp.domain.model.TipoInventario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TipoInventarioViewModel @Inject constructor(private val objRepo: TipoInventarioRepositorio): ViewModel() {

    val listarTipoInventario=objRepo.ListarTipoinventario().asLiveData()

    fun RegistraTipoInventario(tipoinventario: TipoInventario){
        viewModelScope.launch {  objRepo.insertTipoInventario(tipoinventario)}
    }
}