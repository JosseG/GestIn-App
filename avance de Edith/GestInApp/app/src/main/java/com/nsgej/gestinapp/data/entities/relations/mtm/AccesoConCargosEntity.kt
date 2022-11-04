package com.nsgej.gestinapp.data.entities.relations.mtm

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.nsgej.gestinapp.data.entities.AccesoCargoEntity
import com.nsgej.gestinapp.data.entities.AccesoEntity
import com.nsgej.gestinapp.data.entities.CargoEntity

data class AccesoConCargosEntity(
    @Embedded
    val acceso: AccesoEntity,
    @Relation(
        parentColumn = "id_acceso",
        entityColumn = "id_cargo",
        associateBy = Junction(AccesoCargoEntity::class)
    )
    val cargos: List<CargoEntity>
)
