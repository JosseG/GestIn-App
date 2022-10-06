package com.nsgej.gestinapp.data.entities.relations.otm

import androidx.room.Embedded
import androidx.room.Relation
import com.nsgej.gestinapp.data.entities.InventarioEntity
import com.nsgej.gestinapp.data.entities.ProductoEntity
import com.nsgej.gestinapp.data.entities.TipoProductoEntity

class TipoProductoConInventariosEntity(
    @Embedded val tipoproducto: TipoProductoEntity,
    @Relation(
        parentColumn = "id_tipoprod",
        entityColumn = "id_tipoprod"
    )
    val inventarios: List<InventarioEntity>
)