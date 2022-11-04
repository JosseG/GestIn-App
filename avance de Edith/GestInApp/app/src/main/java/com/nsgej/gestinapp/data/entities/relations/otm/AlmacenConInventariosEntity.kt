package com.nsgej.gestinapp.data.entities.relations.otm

import androidx.room.Embedded
import androidx.room.Relation
import com.nsgej.gestinapp.data.entities.AlmacenEntity
import com.nsgej.gestinapp.data.entities.InventarioEntity

data class AlmacenConInventariosEntity(
    @Embedded val almacen: AlmacenEntity,
    @Relation(
        parentColumn = "id_almacen",
        entityColumn = "id_almacen"
    )
    val inventarios: List<InventarioEntity>
)