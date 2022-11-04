package com.nsgej.gestinapp.data.repository

import android.provider.AlarmClock
import com.nsgej.gestinapp.data.dao.AlmacenDao
import com.nsgej.gestinapp.data.entities.toEntity
import com.nsgej.gestinapp.domain.model.Almacen
import com.nsgej.gestinapp.domain.model.Cargo
import com.nsgej.gestinapp.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AlmacenRepositorio @Inject constructor(
    private val almacenDao: AlmacenDao
) {

    fun obtenerAlmacenes() : Flow<List<Almacen>> {
        var respuesta = almacenDao.obtenerAlmacenes().map{
            it.map {
                    e -> e.toDomain()
            }
        };
        return respuesta
    }

    suspend fun obtenerAlmacen(id : String) : Almacen {
        var respuesta = almacenDao.obtenerAlmacenPorId(id).toDomain()

        return respuesta
    }

    suspend fun insertarAlmacen(almacen: Almacen) {
        almacenDao.agregarAlmacen(almacen.toEntity())
    }

    suspend fun insertarAlmacenes(almacenes: List<Almacen>) {
        almacenDao.agregarAlmacenes(almacenes.map { it.toEntity() })
    }

}