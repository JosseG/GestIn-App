package com.nsgej.gestinapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nsgej.gestinapp.data.entities.EmpleadoEntity
import com.nsgej.gestinapp.data.entities.UsuarioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {

    @Query("select * from tb_usuario")
    fun obtenerUsuarios() : Flow<List<UsuarioEntity>>

    @Query("select * from tb_usuario where id_usuario= :id")
    fun obtenerUsuarioPorId(id: String) : Flow<UsuarioEntity>

    @Query("select * from tb_usuario where alias_usuario= :alias and contrasena_usuario= :contrasena")
    fun obtenerUsuarioPorAliasYContrasena(alias: String,contrasena: String) : Flow<UsuarioEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarUsuario(usuario: UsuarioEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarUsuarios(usuarios: List<UsuarioEntity>)

}