package com.nsgej.gestinapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.nsgej.gestinapp.domain.model.Cargo
import com.nsgej.gestinapp.domain.model.Usuario

@Entity(tableName = "tb_cargo",indices = [Index(value = ["nombre_cargo"],unique = true)])
data class CargoEntity (
    @PrimaryKey
    @ColumnInfo(name = "id_cargo") val id: String,
    @ColumnInfo(name = "nombre_cargo") val nombre: String,
    @ColumnInfo(name = "estado") val estado: Boolean = true
)

fun Cargo.toEntity() = CargoEntity( id,nombre,estado)
