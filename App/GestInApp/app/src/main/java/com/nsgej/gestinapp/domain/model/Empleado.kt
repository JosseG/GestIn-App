package com.nsgej.gestinapp.domain.model

import com.beust.klaxon.Klaxon
import com.nsgej.gestinapp.data.entities.EmpleadoEntity

private val klaxon = Klaxon()

class Empleado(val id: String, var idAlmacen: String, val nombre: String, val apellido: String, val correo: String, val telefono: String, val estado: Boolean) {

    override fun toString(): String {
        return "Empleado(id='$id', idAlmacen='$idAlmacen', nombre='$nombre', apellido='$apellido', correo='$correo', telefono='$telefono', estado=$estado)"
    }

    fun toJson() = klaxon.toJsonString(this)

    companion object {
        fun fromJson(json: String) = klaxon.parse<Empleado>(json)
    }

}

fun EmpleadoEntity.toDomain() = Empleado(id,idAlmacen,nombre,apellido, correo, telefono, estado)