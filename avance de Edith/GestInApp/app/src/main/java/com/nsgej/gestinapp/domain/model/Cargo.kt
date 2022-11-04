package com.nsgej.gestinapp.domain.model

import com.beust.klaxon.Klaxon
import com.nsgej.gestinapp.data.entities.AlmacenEntity
import com.nsgej.gestinapp.data.entities.CargoEntity

private val klaxon = Klaxon()

class Cargo(val id: String, val nombre: String, val estado: Boolean ){

    override fun toString(): String {
        return "Cargo(id='$id', nombre='$nombre', estado=$estado)"
    }

    fun toJson() = klaxon.toJsonString(this)

    companion object {
        fun fromJson(json: String) = klaxon.parse<Cargo>(json)
    }

}

fun CargoEntity.toDomain() = Cargo(id, nombre, estado)