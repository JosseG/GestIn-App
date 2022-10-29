package com.nsgej.gestinapp.data.dao

import androidx.room.*
import com.nsgej.gestinapp.data.entities.TipoProductoEntity
import com.nsgej.gestinapp.data.entities.UsuarioEntity
import com.nsgej.gestinapp.data.entities.relations.otm.EmpleadoConInventariosEntity
import com.nsgej.gestinapp.data.entities.relations.otm.TipoProductoConInventariosEntity
import com.nsgej.gestinapp.data.entities.relations.otm.TipoProductoConProductosEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TipoProductoDao {

    @Query("select * from tb_tipoproducto")
    fun obtenerTiposProducto() : Flow<List<TipoProductoEntity>>

    @Query("select * from tb_tipoproducto where id_tipoprod= :id")
    fun obtenerTipoProductoPorId(id: String) : Flow<TipoProductoEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarTipoProducto(tipoProducto: TipoProductoEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarTiposProducto(tiposProducto: List<TipoProductoEntity>)

    @Transaction
    @Query("select * from tb_tipoproducto where id_tipoprod = :id")
    suspend fun obtenerTipoProductoConInventarios(id : String) : List<TipoProductoConInventariosEntity>

    @Transaction
    @Query("select * from tb_tipoproducto where id_tipoprod = :id")
    suspend fun obtenerTipoProductoConProductos(id : String) : List<TipoProductoConProductosEntity>
}