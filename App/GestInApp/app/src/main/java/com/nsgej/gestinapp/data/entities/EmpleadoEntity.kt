package com.nsgej.gestinapp.data.entities

import androidx.room.*
import com.nsgej.gestinapp.domain.model.Empleado

@Entity(
    tableName = "tb_empleado",foreignKeys = [
        ForeignKey(
            entity = AlmacenEntity::class,
            parentColumns = ["id_almacen"],
            childColumns = ["id_almacen"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["id_almacen"])]
)
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

fun Empleado.toEntity() = EmpleadoEntity(id,idAlmacen,nombre, apellido, correo, telefono, estado)