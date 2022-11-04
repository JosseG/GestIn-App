package com.nsgej.gestinapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_empleado")
data class EmpleadoEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_empleado") val id: String,
    @ColumnInfo(name = "id_almacen") val idAlmacen: String,
    @ColumnInfo(name = "nombre_empleado") val nombre: String,
    @ColumnInfo(name = "apellido_empleado") val apellido: String,
    @ColumnInfo(name = "correo_empleado") val correo: String,
    @ColumnInfo(name = "telefono_empleado") val telefono: String,
    @ColumnInfo(name = "estado") val estado: Boolean = true
)