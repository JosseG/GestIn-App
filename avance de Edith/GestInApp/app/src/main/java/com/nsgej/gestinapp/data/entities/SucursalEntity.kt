package com.nsgej.gestinapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_sucursal")
data class SucursalEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_sucursal") val id: String,
    @ColumnInfo(name = "sector_sucursal") val sector: String,
    @ColumnInfo(name = "estado") val estado: Boolean = true
)