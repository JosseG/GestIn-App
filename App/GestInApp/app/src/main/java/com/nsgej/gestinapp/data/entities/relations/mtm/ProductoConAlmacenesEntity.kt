package com.nsgej.gestinapp.data.entities.relations.mtm

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.nsgej.gestinapp.data.entities.AlmacenEntity
import com.nsgej.gestinapp.data.entities.ProductoAlmacenEntity
import com.nsgej.gestinapp.data.entities.ProductoEntity

class ProductoConAlmacenesEntity(
    @Embedded
    val producto: ProductoEntity,
    @Relation(
        parentColumn = "id_producto",
        entityColumn = "id_almacen",
        associateBy = Junction(ProductoAlmacenEntity::class)
    )
    val almacenes: List<AlmacenEntity>
)