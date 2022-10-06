package com.nsgej.gestinapp.data.entities.relations.mtm

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.nsgej.gestinapp.data.entities.AccesoCargoEntity
import com.nsgej.gestinapp.data.entities.AccesoEntity
import com.nsgej.gestinapp.data.entities.CargoEntity

data class CargoConAccesosEntity(
    @Embedded
    val cargo: CargoEntity,
    @Relation(
        parentColumn = "id_cargo",
        entityColumn = "id_acceso",
        associateBy = Junction(AccesoCargoEntity::class)
    )
    val accesos: List<AccesoEntity>
)