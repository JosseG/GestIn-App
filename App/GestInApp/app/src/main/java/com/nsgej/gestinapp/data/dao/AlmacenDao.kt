package com.nsgej.gestinapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nsgej.gestinapp.data.entities.AccesoEntity
import com.nsgej.gestinapp.data.entities.AlmacenEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlmacenDao {

    @Query("select * from tb_almacen")
    fun obtenerAlmacenes() : Flow<List<AlmacenEntity>>

    @Query("select * from tb_almacen where id_almacen= :id")
    fun obtenerAlmacenPorId(id: String) : Flow<AlmacenEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarAlmacen(almacen: AlmacenEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarAlmacenes(almacenes: List<AlmacenEntity>)

}