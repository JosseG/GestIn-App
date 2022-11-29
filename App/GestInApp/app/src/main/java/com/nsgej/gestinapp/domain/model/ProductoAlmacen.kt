package com.nsgej.gestinapp.domain.model

import com.nsgej.gestinapp.data.entities.ProductoAlmacenEntity
import com.nsgej.gestinapp.data.entities.ProductoEntity

class ProductoAlmacen (val idProducto: String, val idAlmacen: String, var cantidad: Int, val estado : Boolean = true) {

    override fun toString(): String {
        return "ProductoAlmacen(idProducto='$idProducto', idAlmacen='$idAlmacen')"
    }
}

fun ProductoAlmacenEntity.toDomain() = ProductoAlmacen(idProducto, idAlmacen,cantidad, estado)