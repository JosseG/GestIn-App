package com.nsgej.gestinapp.viewmodel.empleado

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nsgej.gestinapp.data.repository.EmpleadoRepositorio
import com.nsgej.gestinapp.domain.model.Empleado
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmpleadoViewModel @Inject constructor(private var empleadoRepository: EmpleadoRepositorio) : ViewModel(){

    val empleados  = empleadoRepository.obtenerEmpleados().asLiveData();


    fun insertar(empleado: Empleado){
        viewModelScope.launch {
            empleadoRepository.insertarEmpleado(empleado)
        }
    }

    suspend fun obtenerEmpleado(id : String) = empleadoRepository.obtenerEmpleado(id)

    fun actualizar(empleado: Empleado){
        viewModelScope.launch {

        }
    }

    fun eliminar(empleado: Empleado){
        viewModelScope.launch {/*
            empleadoRepository.elimi(producto)*/
        }
    }

}