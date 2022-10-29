package com.nsgej.gestinapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nsgej.gestinapp.domain.model.Usuario

@Entity(tableName = "tb_usuario")
class UsuarioEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_usuario") val id: Int=0,
    @ColumnInfo(name = "id_empleado") val idEmpleado: String,
    @ColumnInfo(name = "id_cargo") val idCargo: String,
    @ColumnInfo(name = "alias_usuario") val alias: String,
    @ColumnInfo(name = "contrasena_usuario") val contrasena: String,
    @ColumnInfo(name = "estado") val estado: Boolean = true
)

fun Usuario.toEntity() = UsuarioEntity(idEmpleado = idEmpleado, idCargo = idCargo, alias = alias, contrasena = contrasena, estado = estado)