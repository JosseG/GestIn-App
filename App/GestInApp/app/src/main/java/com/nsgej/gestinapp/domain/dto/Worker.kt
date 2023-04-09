package com.nsgej.gestinapp.domain.dto

import com.nsgej.gestinapp.data.entities.EmpleadoEntity
import com.nsgej.gestinapp.data.entities.relations.oto.EmpleadoYUsuario
import com.nsgej.gestinapp.domain.model.Empleado
import com.nsgej.gestinapp.domain.model.Usuario
import com.nsgej.gestinapp.domain.model.toDomain

class Worker(val empleado: Empleado, val usuario: Usuario) {
}

fun EmpleadoYUsuario.toDomain() = Worker(empleado.toDomain(),usuario.toDomain())