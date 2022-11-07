package com.nsgej.gestinapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nsgej.gestinapp.domain.model.TipoInventario
import com.nsgej.gestinapp.domain.model.TipoProducto

@Entity(tableName = "tb_tipoinventario")
class TipoInventarioEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_tipoinventario") val id: Int=0,
    @ColumnInfo(name = "nombre_tipoinventario") val nombre: String,
    @ColumnInfo(name = "estado") val estado: Boolean = true
)

fun TipoInventario.toEntity() = TipoInventarioEntity(id, nombre, estado)