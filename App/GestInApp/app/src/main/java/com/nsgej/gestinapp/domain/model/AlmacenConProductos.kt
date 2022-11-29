package com.nsgej.gestinapp.domain.model

import com.nsgej.gestinapp.data.entities.AlmacenEntity
import com.nsgej.gestinapp.data.entities.ProductoEntity
import com.nsgej.gestinapp.data.entities.relations.mtm.AlmacenConProductosEntity

class AlmacenConProductos (val almacen: AlmacenEntity,val productos: List<ProductoEntity> ) {
    override fun toString(): String {
        return "AlmacenConProductos(almacen=$almacen, productos=$productos)"
    }
}

fun AlmacenConProductosEntity.toDomain() = AlmacenConProductos(almacen, productos)