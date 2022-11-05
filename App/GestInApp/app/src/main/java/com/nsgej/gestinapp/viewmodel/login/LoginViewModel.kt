package com.nsgej.gestinapp.viewmodel.login

import android.util.Log
import androidx.lifecycle.*
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.data.Respuesta
import com.nsgej.gestinapp.data.repository.*
import com.nsgej.gestinapp.domain.model.*
import com.nsgej.gestinapp.ui.login.LoginResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class LoginViewModel @Inject constructor(private val usuarioRepositorio: UsuarioRepositorio,private val cargoRepositorio: CargoRepositorio,private val empleadoRepositorio: EmpleadoRepositorio, private val almacenRepositorio: AlmacenRepositorio, private val sucursalRepositorio: SucursalRepositorio) : ViewModel() {



    val usuarioMutable = usuarioRepositorio.obtenerUsuarios().asLiveData()

    val sucursalesMutable = sucursalRepositorio.obtenerSucursales().asLiveData()


    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    private var _cargoObtenido = MutableLiveData<Cargo>()

    val cargoObtenido: LiveData<Cargo> = _cargoObtenido


    private var _sucursalObtenido = MutableLiveData<Sucursal>()
    val sucursalObtenido : LiveData<Sucursal> = _sucursalObtenido


    fun insertarUsuariosPorEmpleadosPorCargo(usuarios: List<Usuario>, empleados: List<Empleado>, cargo : Cargo){
        viewModelScope.launch {

            if(cargo.id == ""){
                Log.i("L-usuario", "**")
                return@launch
            }

            if(usuarios.isEmpty() && empleados.isEmpty()){
                return@launch
            }

            if(usuarios.size==empleados.size){

                usuarios.zip(empleados).map {
                    it.first.idEmpleado = it.second.id
                }
                usuarios.map {
                    it.idCargo = cargo.id
                }

                Log.i("L-usuario", "usuarioIng")
                usuarioRepositorio.insertarUsuarios(usuarios)

            }else{
                Log.i("L-usuario", "***")
                return@launch
            }

        }
    }

    fun insertarEmpleadosPorAlmacen(empleados: List<Empleado>, almacen: Almacen){

        viewModelScope.launch {

            if(almacen.id == ""){
                return@launch
            }

            if(empleados.isEmpty()){
                return@launch
            }

            empleados.map {
                it.idAlmacen = almacen.id
            }

            Log.i("L-empleado", "empleadoingres")
            empleadoRepositorio.insertarEmpleados(empleados)

        }

    }

    fun obtenerSucursal () {
        viewModelScope.launch {

            Log.i("sucursales",sucursalRepositorio.obtenerSucursal("S00002").id )

        }

    }

    fun insertarAlmacenesPorSucursal(almacenes : List<Almacen>,sucursal: Sucursal){
        viewModelScope.launch {

            if(sucursal.id == ""){
                return@launch
            }

            if(almacenes.isEmpty()){
                return@launch
            }

            almacenes.map {
                it.idSucursal = sucursal.id
            }
            Log.i("L-almacen", "ingresado")
            almacenRepositorio.insertarAlmacenes(almacenes)
        }
    }

    fun insertarSucursales(sucursales : List<Sucursal>){
        viewModelScope.launch {
            Log.i("L-sucursal","insertado")
            sucursalRepositorio.insertarSucursales(sucursales)

        }
    }

    fun insertarCargos(cargos : List<Cargo>){
        viewModelScope.launch {

            Log.i("L-cargo","insertado")
            cargoRepositorio.insertarCargos(cargos)

        }
    }

    fun obtenerCargo(id : String){
        viewModelScope.launch {
            _cargoObtenido.value = cargoRepositorio.obtenerCargo(id)
        }
    }

    fun obtenerSucursal(id : String){
        viewModelScope.launch {
            _sucursalObtenido.value = sucursalRepositorio.obtenerSucursal(id)
        }
    }

    fun login(alias: String, contrasena: String) {

        viewModelScope.launch {
            val respuesta = usuarioRepositorio.obtenerUsuarioPorAliasYContrasena(alias, contrasena)


            if (respuesta is Respuesta.Success) {
                Log.i("rspta",respuesta.data.toString())
                _loginResult.value = LoginResult(success = respuesta.data)
            } else {
                _loginResult.value = LoginResult(error = 0)
            }
        }
    }



}