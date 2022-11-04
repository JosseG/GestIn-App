package com.nsgej.gestinapp.domain.model

import com.beust.klaxon.Klaxon
import com.nsgej.gestinapp.data.entities.InventarioEntity

private val klaxon = Klaxon()

class Inventario(val id: String, val idProducto: String, val idTipoInventario: Int, val idAlmacen: String, val idEmpleado: String, val cantidad: Int, val descripcion: String, val estado: Boolean) {

    override fun toString(): String {
        return "Inventario(id='$id', idProducto='$idProducto', idTipoInventario=$idTipoInventario, idAlmacen='$idAlmacen', idEmpleado='$idEmpleado', cantidad=$cantidad, descripcion='$descripcion', estado=$estado)"
    }

    fun toJson() = klaxon.toJsonString(this)

    companion object {
        fun fromJson(json: String) = klaxon.parse<Inventario>(json)
    }

}

fun InventarioEntity.toDomain() = Inventario(id, idProducto, idTipoInventario, idAlmacen, idEmpleado, cantidad, descripcion, estado)