package com.nsgej.gestinapp.domain.model

import com.beust.klaxon.Klaxon
import com.nsgej.gestinapp.data.entities.TipoProductoEntity
import com.nsgej.gestinapp.data.entities.UsuarioEntity

private val klaxon = Klaxon()
class TipoProducto(val id: Int, val nombre: String, val estado: Boolean) {

    override fun toString(): String {
        return "TipoProducto(id=$id, nombre='$nombre', estado=$estado)"
    }

    fun toJson() = klaxon.toJsonString(this)

    companion object {
        fun fromJson(json: String) = klaxon.parse<TipoProducto>(json)
    }
}

fun TipoProductoEntity.toDomain() = TipoProducto(id, nombre, estado)