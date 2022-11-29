package com.nsgej.gestinapp.data.entities

import androidx.room.*
import com.nsgej.gestinapp.domain.model.ProductoAlmacen

@Entity(
    tableName = "tb_producto_almacen",
    primaryKeys = ["id_producto", "id_almacen"],
    indices = [Index(value = ["id_almacen"])],
    foreignKeys = [
        ForeignKey(
            entity = ProductoEntity::class,
            parentColumns = ["id_producto"],
            childColumns = ["id_producto"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE

        ),
        ForeignKey(
            entity = AlmacenEntity::class,
            parentColumns = ["id_almacen"],
            childColumns = ["id_almacen"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class ProductoAlmacenEntity(
    @ColumnInfo(name = "id_producto") val idProducto : String,
    @ColumnInfo(name = "id_almacen") val idAlmacen : String,
    @ColumnInfo(name = "cantidad") val cantidad : Int,
    @ColumnInfo(name = "estado") val estado : Boolean = true
)

fun ProductoAlmacen.toEntity() = ProductoAlmacenEntity(idProducto, idAlmacen, cantidad, estado)