package com.nsgej.gestinapp.data.dao

import androidx.room.*
import com.nsgej.gestinapp.data.entities.InventarioEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface InventarioDao {

    @Query("select * from tb_inventario")
    fun obtenerInventarios() : Flow<List<InventarioEntity>>

    @Query("select * from tb_inventario where id_inventario= :id")
    fun obtenerInventarioPorId(id: String) : Flow<InventarioEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarInventario(inventario: InventarioEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarInventarios(inventarios: List<InventarioEntity>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun actualizarInventario(inventario: InventarioEntity)

    @Query("delete from tb_inventario where id_inventario= :cod")
    suspend fun eliminarInventarioPorId(cod: String)


    @Query("DELETE FROM tb_inventario")
    suspend fun borrarTodo()

}