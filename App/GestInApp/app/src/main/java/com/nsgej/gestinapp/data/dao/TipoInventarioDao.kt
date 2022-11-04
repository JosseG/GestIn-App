package com.nsgej.gestinapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nsgej.gestinapp.data.entities.ProductoEntity
import com.nsgej.gestinapp.data.entities.TipoInventarioEntity
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


    @Query("DELETE FROM tb_tipoinventario")
    suspend fun borrarTodo()


}