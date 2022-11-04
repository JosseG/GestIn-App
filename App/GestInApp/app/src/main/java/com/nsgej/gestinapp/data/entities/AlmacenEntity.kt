package com.nsgej.gestinapp.data.entities

import androidx.room.*
import com.nsgej.gestinapp.domain.model.Almacen
import com.nsgej.gestinapp.domain.model.Cargo

@Entity(
    tableName = "tb_almacen",
    foreignKeys = [
        ForeignKey(
            entity = SucursalEntity::class,
            parentColumns = ["id_sucursal"],
            childColumns = ["id_sucursal"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["id_sucursal"])]
)
data class AlmacenEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_almacen") val id: String,
    @ColumnInfo(name = "id_sucursal") val idSucursal: String,
    @ColumnInfo(name = "des_almacen") val descripcion: String,
    @ColumnInfo(name = "direccion_almacen") val direccion: String,
    @ColumnInfo(name = "estado") val estado: Boolean = true
)

fun Almacen.toEntity() = AlmacenEntity( id,idSucursal,descripcion,direccion,estado)