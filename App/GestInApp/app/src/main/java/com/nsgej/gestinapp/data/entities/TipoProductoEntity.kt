package com.nsgej.gestinapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.nsgej.gestinapp.domain.model.TipoProducto

@Entity(tableName = "tb_tipoproducto",indices = [Index(value = ["nombre_tipoprod"], unique = true)])
class TipoProductoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_tipoprod") val id: Int=0,
    @ColumnInfo(name = "nombre_tipoprod") val nombre: String,
    @ColumnInfo(name = "imagenurl_tipoprod") val imagenUrl: String,
    @ColumnInfo(name = "estado") val estado: Boolean = true
)

fun TipoProducto.toEntity() = TipoProductoEntity(id, nombre, imagenUrl, estado)