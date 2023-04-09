package com.nsgej.gestinapp.data.entities.relations.oto

import androidx.room.Embedded
import androidx.room.Relation
import com.nsgej.gestinapp.data.entities.AccesoCargoEntity
import com.nsgej.gestinapp.data.entities.EmpleadoEntity
import com.nsgej.gestinapp.data.entities.UsuarioEntity
import com.nsgej.gestinapp.data.entities.toEntity
import com.nsgej.gestinapp.domain.dto.Worker
import com.nsgej.gestinapp.domain.model.AccesoCargo

class EmpleadoYUsuario(
    @Embedded val empleado: EmpleadoEntity,
    @Relation(
        parentColumn = "id_empleado",
        entityColumn = "id_empleado"
    )
    val usuario: UsuarioEntity
)

fun Worker.toEntity() = EmpleadoYUsuario(empleado.toEntity(),usuario.toEntity())