package com.nsgej.gestinapp.data.entities.relations.otm

import androidx.room.Embedded
import androidx.room.Relation
import com.nsgej.gestinapp.data.entities.EmpleadoEntity
import com.nsgej.gestinapp.data.entities.InventarioEntity
import com.nsgej.gestinapp.data.entities.ProductoEntity

class ProductoConInventariosEntity(
    @Embedded val producto: ProductoEntity,
    @Relation(
        parentColumn = "id_producto",
        entityColumn = "id_producto"
    )
    val inventarios: List<InventarioEntity>
)