package com.nsgej.gestinapp.data.entities.relations.otm

import androidx.room.Embedded
import androidx.room.Relation
import com.nsgej.gestinapp.data.entities.EmpleadoEntity
import com.nsgej.gestinapp.data.entities.InventarioEntity

class EmpleadoConInventariosEntity(
    @Embedded val empleado: EmpleadoEntity,
    @Relation(
        parentColumn = "id_empleado",
        entityColumn = "id_empleado"
    )
    val inventarios: List<InventarioEntity>
)