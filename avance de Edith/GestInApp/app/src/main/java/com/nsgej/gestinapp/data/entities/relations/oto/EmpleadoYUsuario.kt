package com.nsgej.gestinapp.data.entities.relations.oto

import androidx.room.Embedded
import androidx.room.Relation
import com.nsgej.gestinapp.data.entities.EmpleadoEntity
import com.nsgej.gestinapp.data.entities.UsuarioEntity

class EmpleadoYUsuario(
    @Embedded val empleado: EmpleadoEntity,
    @Relation(
        parentColumn = "id_empleado",
        entityColumn = "id_empleado"
    )
    val usuario: UsuarioEntity
)