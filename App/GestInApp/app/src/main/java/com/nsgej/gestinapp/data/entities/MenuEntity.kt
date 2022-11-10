package com.nsgej.gestinapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nsgej.gestinapp.domain.model.Menu

@Entity(tableName = "tb_menu")
data class MenuEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_menu") val id: Int = 0,
    @ColumnInfo(name = "des_menu") val descripcion: String,
    @ColumnInfo(name = "icono_menu") val icono: String ,
    @ColumnInfo(name = "estado") val estado: Boolean = true
)

fun Menu.toEntity() = MenuEntity(id, descripcion, icono, estado)