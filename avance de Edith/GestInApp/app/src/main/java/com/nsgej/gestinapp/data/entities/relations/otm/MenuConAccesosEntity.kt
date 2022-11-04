package com.nsgej.gestinapp.data.entities.relations.otm

import androidx.room.Embedded
import androidx.room.Relation
import com.nsgej.gestinapp.data.entities.AccesoEntity
import com.nsgej.gestinapp.data.entities.MenuEntity

class MenuConAccesosEntity(
    @Embedded val menu: MenuEntity,
    @Relation(
        parentColumn = "id_menu",
        entityColumn = "id_menu"
    )
    val accesos: List<AccesoEntity>
)