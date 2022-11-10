package com.nsgej.gestinapp.data.entities

import androidx.room.*
import com.nsgej.gestinapp.domain.model.Inventario

@Entity(
    tableName = "tb_inventario", foreignKeys = [
        ForeignKey(
            entity = AlmacenEntity::class,
            parentColumns = ["id_almacen"],
            childColumns = ["id_almacen"]
        )
    ],
    indices = [Index(value = ["id_almacen"])]
)
data class InventarioEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_inventario") val id: Int = 0,
    @ColumnInfo(name = "id_producto") val idProducto: String,
    @ColumnInfo(name = "id_tipoinventario") val idTipoInventario: Int,
    @ColumnInfo(name = "id_almacen") val idAlmacen: String,
    @ColumnInfo(name = "id_empleado") val idEmpleado: String,
    @ColumnInfo(name = "cantidad_inventario") val cantidad: Int,
    @ColumnInfo(name = "descripcion_inventario") val descripcion: String = "Otros",
    @ColumnInfo(name = "estado") val estado: Boolean = true
)

fun Inventario.toEntity() = InventarioEntity(
    id,
    idProducto,
    idTipoInventario,
    idAlmacen,
    idEmpleado,
    cantidad,
    descripcion,
    estado
)