package com.nsgej.gestinapp.data.entities.relations.mtm

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.nsgej.gestinapp.data.entities.*

data class AlmacenConProductosEntity (
    @Embedded
    val almacen: AlmacenEntity,
    @Relation(
        parentColumn = "id_almacen",
        entityColumn = "id_producto",
        associateBy = Junction(ProductoAlmacenEntity::class)
    )
    val productos: List<ProductoEntity>
)