package com.nsgej.gestinapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class AccesoCargoEntity(
    @ColumnInfo(name = "id_acceso") val idAcceso: String,
    @ColumnInfo(name = "id_cargo") val idCargo: String,
    @ColumnInfo(name = "estado") val estado: Boolean = true
)