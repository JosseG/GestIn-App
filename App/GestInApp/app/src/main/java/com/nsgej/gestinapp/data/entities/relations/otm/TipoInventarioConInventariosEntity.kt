package com.nsgej.gestinapp.data.entities.relations.otm

import androidx.room.Embedded
import androidx.room.Relation
import com.nsgej.gestinapp.data.entities.InventarioEntity
import com.nsgej.gestinapp.data.entities.TipoInventarioEntity

class TipoInventarioConInventariosEntity(
    @Embedded val tipoInventario: TipoInventarioEntity,
    @Relation(
        entity = InventarioEntity::class,
        parentColumn = "id_tipoinventario",
        entityColumn = "id_tipoinventario"
    )
    val inventarios: List<InventarioEntity>
)