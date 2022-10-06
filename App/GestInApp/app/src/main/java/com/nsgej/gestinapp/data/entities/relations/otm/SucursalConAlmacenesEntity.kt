package com.nsgej.gestinapp.data.entities.relations.otm

import androidx.room.Embedded
import androidx.room.Relation
import com.nsgej.gestinapp.data.entities.AlmacenEntity
import com.nsgej.gestinapp.data.entities.SucursalEntity

data class SucursalConAlmacenesEntity(

    @Embedded val sucursal: SucursalEntity,
    @Relation(
        parentColumn = "id_sucursal",
        entityColumn = "id_sucursal"
    )
    val almacenes: List<AlmacenEntity>

)