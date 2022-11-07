package com.nsgej.gestinapp.domain.model

import androidx.room.ColumnInfo
import com.beust.klaxon.Klaxon
import com.nsgej.gestinapp.data.entities.AccesoCargoEntity
import com.nsgej.gestinapp.data.entities.AlmacenEntity

private val klaxon = Klaxon()

class Almacen(val id: String, var idSucursal: String, val descripcion: String, val direccion: String, val estado: Boolean) {



    override fun toString(): String {
        return descripcion
    }

    fun toJson() = klaxon.toJsonString(this)

    companion object {
        fun fromJson(json: String) = klaxon.parse<Almacen>(json)
    }

}


fun AlmacenEntity.toDomain() = Almacen(id, idSucursal, descripcion, direccion, estado)