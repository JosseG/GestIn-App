package com.nsgej.gestinapp.data.dao

import androidx.room.*
import com.nsgej.gestinapp.data.entities.ProductoEntity
import com.nsgej.gestinapp.data.entities.UsuarioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {

    @Query("select * from tb_usuario")
    fun obtenerUsuarios() : Flow<List<UsuarioEntity>>

    @Query("select * from tb_usuario where id_usuario= :id")
    fun obtenerUsuarioPorId(id: String) : Flow<UsuarioEntity>

    @Query("select * from tb_usuario where alias_usuario= :alias and contrasena_usuario= :contrasena")
    suspend fun obtenerUsuarioPorAliasYContrasena(alias: String,contrasena: String) : UsuarioEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarUsuario(usuario: UsuarioEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarUsuarios(usuarios: List<UsuarioEntity>)

/*
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarUsuariosConEmpleadosPorCargo(usuarios: List<UsuarioEntity>,empleados: List<EmpleadoEntity>,cargo : CargoEntity)
*/
    @Delete(entity = UsuarioEntity::class)
    suspend fun eliminarUsuario(usuario : UsuarioEntity)



    @Query("delete from tb_usuario")
    suspend fun borrarTodo()


}