package com.nsgej.gestinapp.data.repository

import androidx.room.Delete
import com.nsgej.gestinapp.data.dao.ProductoDao
import com.nsgej.gestinapp.data.entities.ProductoEntity
import com.nsgej.gestinapp.data.entities.toEntity
import com.nsgej.gestinapp.domain.model.AlmacenConProductos
import com.nsgej.gestinapp.domain.model.Producto
import com.nsgej.gestinapp.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class   ProductoRepositorio @Inject constructor(private  val objPro: ProductoDao) {

    fun obtenerProductos() : Flow<List<Producto>> {
        val respuesta = objPro.obtenerProductos().map{
            it.map {
                    e -> e.toDomain()
            }
        };
        return respuesta
    }



    suspend fun obtenerTodosProductos(): List<Producto> {
        return objPro.obtenerTodosProductos().map { it.toDomain() }
    }

    suspend fun insertPoducto(producto: Producto){
        objPro.agregarProducto(producto.toEntity())
    }

    suspend fun insertarPoductos(producto: List<Producto>){
        objPro.agregarProductos(producto.map { it.toEntity() })
    }

    suspend fun obtenerProducto(id:String) : Producto {
        val respuesta = objPro.obtenerProductoPorId(id).toDomain()
        return respuesta
    }

    suspend fun actualizarProducto(producto: Producto){
        val respuesta = objPro.actualizarProducto(producto.toEntity())
        return respuesta
    }

    suspend fun eliminarProducto(producto: Producto){
        val respuesta = objPro.eliminarProducto(producto.toEntity())
        return respuesta
    }








}