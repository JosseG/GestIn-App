package com.nsgej.gestinapp.data.entities.relations.otm

import androidx.room.Embedded
import androidx.room.Relation
import com.nsgej.gestinapp.data.entities.ProductoEntity
import com.nsgej.gestinapp.data.entities.TipoProductoEntity

data class TipoProductoConProductosEntity(
    @Embedded val tipoProducto: TipoProductoEntity,
    @Relation(
        parentColumn = "id_tipoprod",
        entityColumn = "id_tipoprod"
    )
    val productos: List<ProductoEntity>
)