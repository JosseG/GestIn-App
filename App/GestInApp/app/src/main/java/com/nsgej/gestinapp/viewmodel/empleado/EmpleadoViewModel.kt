package com.nsgej.gestinapp.viewmodel.empleado

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nsgej.gestinapp.data.repository.EmpleadoRepositorio
import com.nsgej.gestinapp.domain.dto.Worker
import com.nsgej.gestinapp.domain.model.Empleado
import com.nsgej.gestinapp.domain.model.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmpleadoViewModel @Inject constructor(private var empleadoRepository: EmpleadoRepositorio) : ViewModel(){

    val empleados  = empleadoRepository.obtenerEmpleados().asLiveData();


    private var _empleadoObtenidoLiveData = MutableLiveData<Empleado>()
    val empleadoObtenidoLiveData: LiveData<Empleado> = _empleadoObtenidoLiveData

    private var _allDataWObtenidoLiveData = MutableLiveData<Worker>()
    val allDataWObtenidoLiveData: LiveData<Worker> = _allDataWObtenidoLiveData

    private var _ultimoEmpleadoObtenido = MutableLiveData<Empleado>()
    val ultimoEmpleadoObtenido: LiveData<Empleado> = _ultimoEmpleadoObtenido

    private var _empleadosFiltradosXAlmacen = MutableLiveData<List<Empleado>>()
    val empleadosFiltradosXAlmacen: LiveData<List<Empleado>> = _empleadosFiltradosXAlmacen

    private var _workersDataFiltradosXAlmacen = MutableLiveData<List<Worker>>()
    val workersDataFiltradosXAlmacen: LiveData<List<Worker>> = _workersDataFiltradosXAlmacen


    private var _cargoSeleccionado = MutableLiveData<String>()
    val cargoSeleccionado: LiveData<String> = _cargoSeleccionado

    fun insertarEmpleado(empleado: Empleado){
        viewModelScope.launch {
            empleadoRepository.insertarEmpleado(empleado)
        }
    }

    fun seleccionarCargo(cargo: String){
        viewModelScope.launch {
            _cargoSeleccionado.value = cargo
        }
    }



    fun actualizar(empleado: Empleado){
        viewModelScope.launch {
            empleadoRepository.actualizarEmpleado(empleado)
        }
    }

    fun obtenerEmpleadoXId(id : String){
        viewModelScope.launch {
            _empleadoObtenidoLiveData.value=empleadoRepository.obtenerEmpleado(id)
        }
    }

    fun obtenerFullDataEmpleadoXId(id: String){
        viewModelScope.launch {
            _allDataWObtenidoLiveData.value=empleadoRepository.obtenerEmpleadoYUsuario(id)
        }
    }

    fun obtenerUltimoEmpleado(){
        viewModelScope.launch {
            _ultimoEmpleadoObtenido.value = empleadoRepository.obtenerUltimoEmpleado()
        }
    }


    fun obtenerEmpleadosXAlmacen(idAlmacen: String){
        viewModelScope.launch {
            _empleadosFiltradosXAlmacen.value = empleadoRepository.obtenerEmpleadosXAlmacen(idAlmacen)
        }

    }

    fun getAllDataWorkersForWarehouse(idAlmacen: String){
        viewModelScope.launch {
            _workersDataFiltradosXAlmacen.value = empleadoRepository.obtenerEmpleadosYUsuariosXAlmacen(idAlmacen)
        }
    }

    fun eliminar(empleado: Empleado){
        viewModelScope.launch {
            empleadoRepository.eliminarEmpleado(empleado)
        }
    }



}