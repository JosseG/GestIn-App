package com.nsgej.gestinapp.domain.model

import com.beust.klaxon.Klaxon
import com.nsgej.gestinapp.data.entities.AccesoCargoEntity

private val klaxon = Klaxon()

class AccesoCargo(val idAcceso: String, val idCargo: String, val estado: Boolean){

    override fun toString(): String {
        return "AccesoCargo(idAcceso='$idAcceso', idCargo='$idCargo', estado=$estado)"
    }

    fun toJson() = klaxon.toJsonString(this)

    companion object {
        fun fromJson(json: String) = klaxon.parse<AccesoCargo>(json)
    }

}

fun AccesoCargoEntity.toDomain() = AccesoCargo(idAcceso, idCargo, estado)