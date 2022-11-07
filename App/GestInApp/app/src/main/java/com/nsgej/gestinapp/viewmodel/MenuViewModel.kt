package com.nsgej.gestinapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsgej.gestinapp.data.repository.EmpleadoRepositorio
import com.nsgej.gestinapp.domain.model.Empleado
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val empleadoRepositorio: EmpleadoRepositorio) :
    ViewModel() {

    private var _empleado = MutableLiveData<Empleado>()

    val empleado: LiveData<Empleado> = _empleado

    fun obtenerEmpleado(id: String) {
        viewModelScope.launch {

            _empleado.value = empleadoRepositorio.obtenerEmpleado(id)

        }
    }

}