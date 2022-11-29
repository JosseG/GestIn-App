package com.nsgej.gestinapp.viewmodel.empleado

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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


    fun insertarEmpleado(empleado: Empleado){
        viewModelScope.launch {
            empleadoRepository.insertarEmpleado(empleado)
        }
    }

    private var _empleadoObtenidoLiveData = MutableLiveData<Empleado>()

    val empleadoObtenidoLiveData: LiveData<Empleado> = _empleadoObtenidoLiveData


    fun actualizar(empleado: Empleado){
        viewModelScope.launch {

        }
    }

    fun obtenerEmpleadoXId(id : String){
        viewModelScope.launch {
            _empleadoObtenidoLiveData.value=empleadoRepository.obtenerEmpleado(id)
        }
    }

    fun eliminar(empleado: Empleado){
        viewModelScope.launch {/*
            empleadoRepository.elimi(producto)*/
        }
    }

}