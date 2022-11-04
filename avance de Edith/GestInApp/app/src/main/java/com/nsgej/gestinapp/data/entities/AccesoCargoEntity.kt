package com.nsgej.gestinapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_acceso_cargo",primaryKeys = ["id_acceso", "id_cargo"])
data class AccesoCargoEntity(
    @ColumnInfo(name = "id_acceso") val idAcceso: String,
    @ColumnInfo(name = "id_cargo") val idCargo: String,
    @ColumnInfo(name = "estado") val estado: Boolean = true
)