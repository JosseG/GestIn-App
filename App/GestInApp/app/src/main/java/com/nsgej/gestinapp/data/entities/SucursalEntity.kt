package com.nsgej.gestinapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.nsgej.gestinapp.domain.model.Empleado
import com.nsgej.gestinapp.domain.model.Sucursal

@Entity(
    tableName = "tb_sucursal",
    indices = [Index(value = ["sector_sucursal"], unique = true)]
)
data class SucursalEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_sucursal") val id: String,
    @ColumnInfo(name = "sector_sucursal") val sector: String,
    @ColumnInfo(name = "estado") val estado: Boolean = true
)


fun Sucursal.toEntity() = SucursalEntity(id, sector, estado)