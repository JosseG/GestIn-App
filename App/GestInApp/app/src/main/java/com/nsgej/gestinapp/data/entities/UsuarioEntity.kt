package com.nsgej.gestinapp.data.entities

import androidx.room.*
import com.nsgej.gestinapp.domain.model.Usuario

@Entity(
    tableName = "tb_usuario",
    foreignKeys = [
        ForeignKey(
            entity = EmpleadoEntity::class,
            parentColumns = ["id_empleado"],
            childColumns = ["id_empleado"]
        ),
        ForeignKey(
            entity = CargoEntity::class,
            parentColumns = ["id_cargo"],
            childColumns = ["id_cargo"]
        )
    ],
    indices = [Index(value = ["id_empleado"], unique = true),Index(value = ["id_cargo"])]
)
class UsuarioEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_usuario") val id: Int=0,
    @ColumnInfo(name = "id_empleado") val idEmpleado: String,
    @ColumnInfo(name = "id_cargo") val idCargo: String,
    @ColumnInfo(name = "alias_usuario") val alias: String,
    @ColumnInfo(name = "contrasena_usuario") val contrasena: String,
    @ColumnInfo(name = "estado") val estado: Boolean = true
)

fun Usuario.toEntity() = UsuarioEntity(idEmpleado = idEmpleado, idCargo = idCargo, alias = alias, contrasena = contrasena, estado = estado)