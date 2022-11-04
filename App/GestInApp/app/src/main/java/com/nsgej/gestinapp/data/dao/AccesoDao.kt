package com.nsgej.gestinapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nsgej.gestinapp.data.entities.AccesoEntity
import com.nsgej.gestinapp.data.entities.UsuarioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccesoDao {

    @Query("select * from tb_acceso")
    fun obtenerAccesos() : Flow<List<AccesoEntity>>

    @Query("select * from tb_acceso where id_acceso= :id")
    fun obtenerAccesoPorId(id: String) : Flow<AccesoEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarAcceso(acceso: AccesoEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarAccesos(accesos: List<AccesoEntity>)

    @Query("DELETE FROM tb_acceso")
    suspend fun borrarTodo()

}