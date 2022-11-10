package com.nsgej.gestinapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.nsgej.gestinapp.domain.model.AccesoCargo

@Entity(
    tableName = "tb_acceso_cargo",
    primaryKeys = ["id_acceso", "id_cargo"],
    indices = [Index(value = ["id_cargo"])],
    foreignKeys = [
        ForeignKey(
            entity = AccesoEntity::class,
            parentColumns = ["id_acceso"],
            childColumns = ["id_acceso"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE

        ),
        ForeignKey(
            entity = CargoEntity::class,
            parentColumns = ["id_cargo"],
            childColumns = ["id_cargo"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class AccesoCargoEntity(
    @ColumnInfo(name = "id_acceso") val idAcceso: String,
    @ColumnInfo(name = "id_cargo") val idCargo: String,
    @ColumnInfo(name = "estado") val estado: Boolean = true
)

fun AccesoCargo.toEntity() = AccesoCargoEntity( idAcceso, idCargo, estado)