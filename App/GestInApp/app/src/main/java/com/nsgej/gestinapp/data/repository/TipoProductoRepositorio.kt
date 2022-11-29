package com.nsgej.gestinapp.data.repository

import com.nsgej.gestinapp.data.dao.TipoProductoDao
import com.nsgej.gestinapp.data.entities.relations.otm.TipoProductoConProductosEntity
import com.nsgej.gestinapp.data.entities.toEntity
import com.nsgej.gestinapp.domain.model.TipoProducto
import com.nsgej.gestinapp.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TipoProductoRepositorio @Inject constructor(
    val tipoProductoDao: TipoProductoDao
)  {

    fun obtenerTipoProductos() : Flow<List<TipoProducto>> {
        var respuesta = tipoProductoDao.obtenerTiposProducto().map{
            it.map {
                    e -> e.toDomain()
            }
        };
        return respuesta
    }

    suspend fun insertarTipoProducto(tipoProducto: TipoProducto){
        tipoProductoDao.agregarTipoProducto(tipoProducto.toEntity())
    }

    suspend fun insertarTiposProducto(tiposProducto: List<TipoProducto>){
        tipoProductoDao.agregarTiposProducto(tiposProducto.map { it.toEntity() })
    }

    suspend fun obtenerProductosPorTipoProducto(id : Int): TipoProductoConProductosEntity{
        return tipoProductoDao.obtenerTipoProductoConProductos(id)
    }

}