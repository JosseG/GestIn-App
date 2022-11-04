package com.nsgej.gestinapp.data.repository

import com.nsgej.gestinapp.data.dao.SucursalDao
import com.nsgej.gestinapp.data.entities.toEntity
import com.nsgej.gestinapp.domain.model.Sucursal
import com.nsgej.gestinapp.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SucursalRepositorio @Inject constructor(
    private val sucursalDao: SucursalDao
) {

    fun obtenerSucursales() : Flow<List<Sucursal>> {
        var respuesta = sucursalDao.obtenerSucursales().map{
            it.map {
                    e -> e.toDomain()
            }
        };
        return respuesta
    }

    suspend fun obtenerSucursal(id: String?): Sucursal {
        return sucursalDao.obtenerSucursalPorId(id).toDomain()
    }

    suspend fun insertarSucursal(sucursal : Sucursal) {
        sucursalDao.agregarSucursal(sucursal.toEntity())
    }

    suspend fun insertarSucursales(sucursales : List<Sucursal>) {
        sucursalDao.agregarSucursales(sucursales.map { it.toEntity() })
    }


}