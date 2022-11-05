package com.nsgej.gestinapp.data.dao

import androidx.room.*
import com.nsgej.gestinapp.data.entities.EmpleadoEntity
import com.nsgej.gestinapp.data.entities.InventarioEntity
import com.nsgej.gestinapp.data.entities.relations.otm.AlmacenConEmpleadosEntity
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


    @Query("DELETE FROM tb_inventario")
    suspend fun borrarTodo()

}