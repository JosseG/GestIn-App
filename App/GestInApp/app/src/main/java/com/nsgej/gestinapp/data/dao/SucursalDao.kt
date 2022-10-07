package com.nsgej.gestinapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nsgej.gestinapp.data.entities.SucursalEntity
import com.nsgej.gestinapp.data.entities.UsuarioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SucursalDao {

    @Query("select * from tb_sucursal")
    fun obtenerSucursales() : Flow<List<SucursalEntity>>

    @Query("select * from tb_sucursal where id_sucursal= :id")
    fun obtenerSucursalPorId(id: String) : Flow<SucursalEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarSucursal(sucursal: SucursalEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarSucursales(sucursales: List<SucursalEntity>)



}