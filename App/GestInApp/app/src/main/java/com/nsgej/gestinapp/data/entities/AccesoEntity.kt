package com.nsgej.gestinapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_acceso")
data class AccesoEntity(
    @PrimaryKey()
    @ColumnInfo(name = "id_acceso") val id : String ="",
    @ColumnInfo(name = "id_menu") val idMenu : Int = 0,
    @ColumnInfo(name = "nombre_acceso") val nombre : String = "",
    @ColumnInfo(name = "estado_acceso") val estado : Boolean = true
)