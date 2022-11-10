package com.nsgej.gestinapp.domain.model

import com.beust.klaxon.Klaxon
import com.nsgej.gestinapp.data.entities.AccesoEntity


private val klaxon = Klaxon()

class Acceso(val id : String, val idMenu : Int, val nombre : String, val estado : Boolean) {

    override fun toString(): String {
        return "Acceso(id='$id', idMenu=$idMenu, nombre='$nombre', estado=$estado)"
    }

    fun toJson() = klaxon.toJsonString(this)

    companion object {
        fun fromJson(json: String) = klaxon.parse<Acceso>(json)
    }

}

fun AccesoEntity.toDomain() = Acceso(id, idMenu, nombre, estado)