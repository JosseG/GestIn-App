package com.nsgej.gestinapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.nsgej.gestinapp.domain.model.Producto

@Entity(tableName = "tb_producto",indices = [Index(value = ["codigobar_producto"], unique = true)])
class ProductoEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_producto") val id: String,
    @ColumnInfo(name = "id_tipoprod") val idTipoProducto: Int,
    @ColumnInfo(name = "codigobar_producto") val codigoBarra: String,
    @ColumnInfo(name = "descripcion_producto") val descripcion: String,
    @ColumnInfo(name = "marca_producto") val marca: String,
    @ColumnInfo(name = "estado") val estado: Boolean = true
)

fun Producto.toEntity() = ProductoEntity(id, idTipoProducto, codigoBarra, descripcion, marca, estado)