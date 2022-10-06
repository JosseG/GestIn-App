package com.nsgej.gestinapp.data.entities.relations.otm

import androidx.room.Embedded
import androidx.room.Relation
import com.nsgej.gestinapp.data.entities.CargoEntity
import com.nsgej.gestinapp.data.entities.UsuarioEntity

class CargoConUsuariosEntity(
    @Embedded val cargo: CargoEntity,
    @Relation(
        parentColumn = "id_cargo",
        entityColumn = "id_cargo"
    )
    val usuarios: List<UsuarioEntity>
)