package com.nsgej.gestinapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.nsgej.gestinapp.data.entities.AccesoEntity
import com.nsgej.gestinapp.data.entities.AlmacenEntity
import com.nsgej.gestinapp.data.entities.relations.otm.AlmacenConEmpleadosEntity
import com.nsgej.gestinapp.data.entities.relations.otm.AlmacenConInventariosEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlmacenDao {

    @Query("select * from tb_almacen")
    fun obtenerAlmacenes() : Flow<List<AlmacenEntity>>

    @Query("select * from tb_almacen where id_almacen= :id")
    suspend fun obtenerAlmacenPorId(id: String) : AlmacenEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarAlmacen(almacen: AlmacenEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarAlmacenes(almacenes: List<AlmacenEntity>)

    @Transaction
    @Query("select * from tb_almacen where id_almacen = :id")
    suspend fun obtenerAlmacenConEmpleados(id : String) : List<AlmacenConEmpleadosEntity>

    @Transaction
    @Query("select * from tb_almacen where id_almacen = :id")
    suspend fun obtenerAlmacenConInventarios(id : String) : List<AlmacenConInventariosEntity>

    @Query("DELETE FROM tb_almacen")
    suspend fun borrarTodo()

}