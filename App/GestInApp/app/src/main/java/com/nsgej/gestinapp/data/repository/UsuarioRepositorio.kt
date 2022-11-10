package com.nsgej.gestinapp.data.repository

import android.util.Log
import com.nsgej.gestinapp.data.Respuesta
import com.nsgej.gestinapp.data.dao.UsuarioDao
import com.nsgej.gestinapp.data.entities.toEntity
import com.nsgej.gestinapp.domain.model.Usuario
import com.nsgej.gestinapp.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class UsuarioRepositorio @Inject constructor(
    val usuarioDao: UsuarioDao
)  {

    fun obtenerUsuarios() : Flow<List<Usuario>> {
        var respuesta = usuarioDao.obtenerUsuarios().map{
                it.map {
                    e -> e.toDomain()
                }
        };
        return respuesta
    }

    suspend fun obtenerUsuarioPorAliasYContrasena(username : String, password : String) : Respuesta<Usuario>{

        return try{
            val usuario = usuarioDao.obtenerUsuarioPorAliasYContrasena(username,password).toDomain()
            Log.i("repositorio", usuario.toString())
            Respuesta.Success(usuario)
        } catch (e : Throwable){
            Respuesta.Error(Exception("Error en el login",e))
        }

    }

    suspend fun insertarUsuario(usuario : Usuario) {
        usuarioDao.agregarUsuario(usuario.toEntity())
    }

    suspend fun insertarUsuarios(usuario : List<Usuario>) {
        usuarioDao.agregarUsuarios(usuario.map { it.toEntity() })
    }



}