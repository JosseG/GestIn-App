package com.nsgej.gestinapp.data.repository

import com.nsgej.gestinapp.data.dao.ProductoAlmacenDao
import com.nsgej.gestinapp.data.dao.ProductoDao
import com.nsgej.gestinapp.data.entities.ProductoAlmacenEntity
import com.nsgej.gestinapp.data.entities.relations.mtm.AlmacenConProductosEntity
import com.nsgej.gestinapp.data.entities.relations.otm.TipoProductoConProductosEntity
import com.nsgej.gestinapp.data.entities.toEntity
import com.nsgej.gestinapp.domain.model.AlmacenConProductos
import com.nsgej.gestinapp.domain.model.ProductoAlmacen
import com.nsgej.gestinapp.domain.model.TipoProducto
import com.nsgej.gestinapp.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductoAlmacenRepositorio @Inject constructor(private  val objProdAlmacen: ProductoAlmacenDao)
{
/*    fun obtenerProductosPorAlmacens() : Flow<List<TipoProducto>> {
        var respuesta = objProdAlmacen.().map{
            it.map {
                    e -> e.toDomain()
            }
        };
        return respuesta
    }*/

    suspend fun obtenerProductosPorAlmacen(id : String): List<AlmacenConProductos> {
        return objProdAlmacen.obtenerAlmacenConProductos(id).map { it.toDomain() }
    }

    suspend fun actualizarProductoPorAlmacen(productoAlmacen: ProductoAlmacen){
        objProdAlmacen.update(productoAlmacen.toEntity())
    }

    suspend fun eliminarProductoPorAlmacen(productoAlmacen: ProductoAlmacen){
        objProdAlmacen.delete(productoAlmacen.toEntity())
    }

    suspend fun insertarProductoAlmacen(productoAlmacen: ProductoAlmacen){
        objProdAlmacen.insert(productoAlmacen.toEntity())
    }

    suspend fun insertarProductosAlmacenes(productosAlmacenes: List<ProductoAlmacen>){
        objProdAlmacen.insertAll(productosAlmacenes.map { it.toEntity() })
    }

    suspend fun obtenerTodos() : List<ProductoAlmacen>{
        return objProdAlmacen.obtenerTodos().map { it.toDomain() }
    }

    suspend fun obtener(idprod : String, idalmacen : String) : ProductoAlmacen{
        return objProdAlmacen.obtener(idprod,idalmacen).toDomain()
    }

}