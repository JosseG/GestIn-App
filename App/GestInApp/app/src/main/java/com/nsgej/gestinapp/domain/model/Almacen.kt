package com.nsgej.gestinapp.domain.model

import androidx.room.ColumnInfo
import com.beust.klaxon.Klaxon

private val klaxon = Klaxon()

class Almacen(val id: String, val idSucursal: String, val descripcion: String, val direccion: String, val estado: Boolean) {


    override fun toString(): String {
        return "Almacen(id='$id', idSucursal='$idSucursal', descripcion='$descripcion', direccion='$direccion', estado=$estado)"
    }

    fun toJson() = klaxon.toJsonString(this)

    companion object {
        fun fromJson(json: String) = klaxon.parse<Almacen>(json)
    }

}