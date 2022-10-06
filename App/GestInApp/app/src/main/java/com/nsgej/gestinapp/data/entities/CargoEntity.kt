package com.nsgej.gestinapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_cargo")
data class CargoEntity (
    @PrimaryKey
    @ColumnInfo(name = "id_cargo") val id: String,
    @ColumnInfo(name = "nombre_cargo") val nombre: String,
    @ColumnInfo(name = "estado") val estado: Boolean = true
)