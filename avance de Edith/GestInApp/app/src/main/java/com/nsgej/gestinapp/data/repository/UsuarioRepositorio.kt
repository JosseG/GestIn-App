package com.nsgej.gestinapp.data.repository

import com.nsgej.gestinapp.data.dao.UsuarioDao
import com.nsgej.gestinapp.data.entities.UsuarioEntity
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

    suspend fun insertarUsuario(usuario : Usuario) {
        usuarioDao.agregarUsuario(usuario.toEntity())
    }

}