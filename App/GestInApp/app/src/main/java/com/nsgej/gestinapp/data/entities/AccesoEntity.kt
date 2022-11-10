package com.nsgej.gestinapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.nsgej.gestinapp.domain.model.Acceso

@Entity(tableName = "tb_acceso",indices = [Index(value = ["nombre_acceso"],unique = true)])
data class AccesoEntity(
    @PrimaryKey()
    @ColumnInfo(name = "id_acceso") val id : String,
    @ColumnInfo(name = "id_menu") val idMenu : Int,
    @ColumnInfo(name = "nombre_acceso") val nombre : String,
    @ColumnInfo(name = "estado_acceso") val estado : Boolean = true
)

fun Acceso.toEntity() = AccesoEntity( id,idMenu, nombre, estado)