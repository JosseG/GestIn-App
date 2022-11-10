package com.nsgej.gestinapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.nsgej.gestinapp.domain.model.TipoInventario

@Entity(tableName = "tb_tipoinventario",indices = [Index(
    value = ["nombre_tipoinventario"],
    unique = true
)])
class TipoInventarioEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_tipoinventario") val id: Int=0,
    @ColumnInfo(name = "nombre_tipoinventario") val nombre: String,
    @ColumnInfo(name = "estado") val estado: Boolean = true
)

fun TipoInventario.toEntity() = TipoInventarioEntity(id, nombre, estado)