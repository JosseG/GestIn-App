package com.nsgej.gestinapp.domain.model

import com.beust.klaxon.Klaxon
import com.nsgej.gestinapp.data.entities.SucursalEntity

private val klaxon = Klaxon()

class Sucursal(val id: String, val sector: String, val estado: Boolean) {

    override fun toString(): String {
        return "Sucursal(id='$id', sector='$sector', estado=$estado)"
    }

    fun toJson() = klaxon.toJsonString(this)

    companion object {
        fun fromJson(json: String) = klaxon.parse<Sucursal>(json)
    }
}

fun SucursalEntity.toDomain() = Sucursal(id, sector, estado)