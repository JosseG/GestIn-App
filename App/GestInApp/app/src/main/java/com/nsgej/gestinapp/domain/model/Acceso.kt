package com.nsgej.gestinapp.domain.model

class Acceso(val id : String, val idMenu : Int, val nombre : String, val estado : Boolean) {

    override fun toString(): String {
        return "Acceso(id='$id', idMenu=$idMenu, nombre='$nombre', estado=$estado)"
    }



}