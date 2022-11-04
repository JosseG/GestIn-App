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

     var sucursal : String = ""

    suspend fun cargo(id : String) : Cargo = cargoRepositorio.obtenerCargo(id)

    private var cargoObtenido = MutableLiveData<Cargo>()


    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun observeSearchCargoLiveData(): LiveData<Cargo> {
        return cargoObtenido
    }

    /* var cargo : Cargo = Cargo("","",true)*/

/*    fun insertarUsuario ( usuario : Usuario){
        viewModelScope.launch {
            usuarioRepositorio.insertarUsuario(usuario)
        }
    }*/

    /*fun obtenerUsuarioPorAliasYContrasena(usuario : String,contrasena : String){
        usuarioRepositorio.obtenerUsuariosPorAliasYContrasena(usuario,contrasena)
    }*/

    /*fun insertarUsuariosPorCargo(usuarios : List<Usuario>, cargo : Cargo){
        viewModelScope.launch {
            cargoRepositorio.insertarCargo(cargo)
            usuarios.forEach { it.idCargo = cargo.id }
            usuarioRepositorio.insertarUsuarios(usuarios)
        }
    }*/

    fun insertarUsuariosPorEmpleadosPorCargo(usuarios: List<Usuario>, empleados: List<Empleado>, cargo : Cargo){
        viewModelScope.launch {

            val cargoEncontrado = cargoRepositorio.obtenerCargo(cargo.id)

            if(cargoEncontrado.id == ""){
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
                    it.idCargo = cargoEncontrado.id
                }

                usuarioRepositorio.insertarUsuarios(usuarios)

            }else{
                return@launch
            }

        }
    }

    fun insertarEmpleadosPorAlmacen(empleados: List<Empleado>, almacen: Almacen){

        viewModelScope.launch {
            val almacenEncontrado = almacenRepositorio.obtenerAlmacen(almacen.id)

            if(almacenEncontrado.id == ""){
                return@launch
            }

            if(empleados.isEmpty()){
                return@launch
            }

            empleados.map {
                it.idAlmacen = almacenEncontrado.id
            }

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
            val sucursalEncontrado = sucursalRepositorio.obtenerSucursal(sucursal.id)

            Log.i("sucursal", sucursalEncontrado.toString())
            if(sucursalEncontrado.id == ""){
                return@launch
            }

            if(almacenes.isEmpty()){
                return@launch
            }

            almacenes.map {
                it.idSucursal = sucursalEncontrado.id
            }
            almacenRepositorio.insertarAlmacenes(almacenes)
        }
    }

    fun insertarSucursales(sucursales : List<Sucursal>){
        viewModelScope.launch {

            sucursalRepositorio.insertarSucursales(sucursales)

        }
    }

    fun insertarCargos(cargos : List<Cargo>){
        viewModelScope.launch {

            cargoRepositorio.insertarCargos(cargos)

        }
    }

    fun obtenerCargo(id : String){
        viewModelScope.launch {

            cargoObtenido.value = cargoRepositorio.obtenerCargo(id)

        }
    }

    fun login(alias: String, contrasena: String) {
        // can be launched in a separate asynchronous job
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