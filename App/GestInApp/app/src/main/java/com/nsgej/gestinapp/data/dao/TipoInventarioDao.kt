package com.nsgej.gestinapp.data.dao

import androidx.room.*
import com.nsgej.gestinapp.data.entities.TipoInventarioEntity
import com.nsgej.gestinapp.data.entities.relations.otm.TipoInventarioConInventariosEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TipoInventarioDao {

    @Query("select * from tb_tipoinventario")
    fun obtenerTiposInventario() : Flow<List<TipoInventarioEntity>>

    @Query("select * from tb_tipoinventario where id_tipoinventario = :id")
    fun obtenerTipoInventarioPorId(id: String) : Flow<TipoInventarioEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarTipoInventario(tipoInventario: TipoInventarioEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarTiposInventario(tipoInventarios: List<TipoInventarioEntity>)

    @Transaction
    @Query("select * from tb_tipoinventario where id_tipoinventario = :id")
    suspend fun obtenerTipoInventarioConInventarios(id : String) : List<TipoInventarioConInventariosEntity>

    @Query("DELETE FROM tb_tipoinventario")
    suspend fun borrarTodo()


}