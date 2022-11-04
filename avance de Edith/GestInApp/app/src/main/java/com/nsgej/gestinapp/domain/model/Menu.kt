package com.nsgej.gestinapp.domain.model

import com.beust.klaxon.Klaxon
import com.nsgej.gestinapp.data.entities.MenuEntity

private val klaxon = Klaxon()

class Menu(val id: Int = 0, val descripcion: String, val icono: String, val estado: Boolean) {

    override fun toString(): String {
        return "Menu(id=$id, descripcion='$descripcion', icono='$icono', estado=$estado)"
    }

    fun toJson() = klaxon.toJsonString(this)

    companion object {
        fun fromJson(json: String) = klaxon.parse<Menu>(json)
    }
}

fun MenuEntity.toDomain() = Menu(id, descripcion, icono, estado)