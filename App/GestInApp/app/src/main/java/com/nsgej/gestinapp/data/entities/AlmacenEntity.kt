package com.nsgej.gestinapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_almacen")
data class AlmacenEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_almacen") val id: String,
    @ColumnInfo(name = "id_sucursal") val idSucursal: String,
    @ColumnInfo(name = "des_almacen") val descripcion: String,
    @ColumnInfo(name = "direccion_almacen") val direccion: String,
    @ColumnInfo(name = "estado") val estado: Boolean = true
)