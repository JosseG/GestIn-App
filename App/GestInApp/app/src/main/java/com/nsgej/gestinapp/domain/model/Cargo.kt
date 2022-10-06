package com.nsgej.gestinapp.domain.model

import com.beust.klaxon.Klaxon

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