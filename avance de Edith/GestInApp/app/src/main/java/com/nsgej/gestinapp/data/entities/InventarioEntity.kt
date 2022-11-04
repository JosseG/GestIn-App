package com.nsgej.gestinapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_inventario")
data class InventarioEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_inventario") val id: String,
    @ColumnInfo(name = "id_producto") val idProducto: String,
    @ColumnInfo(name = "id_tipoinventario") val idTipoInventario: Int,
    @ColumnInfo(name = "id_almacen") val idAlmacen: String,
    @ColumnInfo(name = "id_empleado") val idEmpleado: String,
    @ColumnInfo(name = "cantidad_inventario") val cantidad: Int,
    @ColumnInfo(name = "descripcion_inventario") val descripcion: String,
    @ColumnInfo(name = "estado") val estado: Boolean = true
)