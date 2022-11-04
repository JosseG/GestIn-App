package com.nsgej.gestinapp.data.dao

import androidx.room.*
import com.nsgej.gestinapp.data.entities.SucursalEntity
import com.nsgej.gestinapp.data.entities.UsuarioEntity
import com.nsgej.gestinapp.data.entities.relations.otm.EmpleadoConInventariosEntity
import com.nsgej.gestinapp.data.entities.relations.otm.SucursalConAlmacenesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SucursalDao {

    @Query("select * from tb_sucursal")
    fun obtenerSucursales() : Flow<List<SucursalEntity>>

    @Query("select * from tb_sucursal where id_sucursal = :id")
    suspend fun obtenerSucursalPorId(id: String?) : SucursalEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarSucursal(sucursal: SucursalEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarSucursales(sucursales: List<SucursalEntity>)

    @Transaction
    @Query("select * from tb_sucursal where id_sucursal = :id")
    suspend fun obtenerSucursalConAlmacenes(id : String) : List<SucursalConAlmacenesEntity>

    @Query("DELETE FROM tb_sucursal")
    suspend fun borrarTodo()

}