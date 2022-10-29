package com.nsgej.gestinapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_tipoproducto")
class TipoProductoEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_tipoprod") val id: Int=0,
    @ColumnInfo(name = "nombre_tipoprod") val nombre: String,
    @ColumnInfo(name = "estado") val estado: Boolean = true
)