package com.nsgej.gestinapp.data.repository

import com.nsgej.gestinapp.data.dao.ProductoDao
import com.nsgej.gestinapp.data.dao.TipoInventarioDao
import com.nsgej.gestinapp.data.entities.ProductoEntity
import com.nsgej.gestinapp.data.entities.TipoInventarioEntity
import com.nsgej.gestinapp.data.entities.toEntity
import com.nsgej.gestinapp.domain.model.Empleado
import com.nsgej.gestinapp.domain.model.Producto
import com.nsgej.gestinapp.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class   ProductoRepositorio @Inject constructor(private  val objPro: ProductoDao) {

    fun obtenerProductos() : Flow<List<Producto>> {
        var respuesta = objPro.obtenerProductos().map{
            it.map {
                    e -> e.toDomain()
            }
        };
        return respuesta
    }

    suspend fun insertPoducto(producto: Producto){
        objPro.agregarProducto(producto.toEntity())
    }
}