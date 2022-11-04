package com.nsgej.gestinapp.domain.model

import com.beust.klaxon.Klaxon
import com.nsgej.gestinapp.data.entities.UsuarioEntity

private val klaxon = Klaxon()
class Usuario(val id: Int=0, var idEmpleado: String, var idCargo: String, val alias: String, val contrasena: String, val estado: Boolean) {

    override fun toString(): String {
        return "Usuario(id=$id, idEmpleado='$idEmpleado', idCargo='$idCargo', alias='$alias', contrasena='$contrasena', estado=$estado)"
    }

    fun toJson() = klaxon.toJsonString(this)

    companion object {
        fun fromJson(json: String) = klaxon.parse<Usuario>(json)
    }

}

fun UsuarioEntity.toDomain() = Usuario(id, idEmpleado, idCargo, alias, contrasena, estado)