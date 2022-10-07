package com.nsgej.gestinapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nsgej.gestinapp.data.entities.TipoProductoEntity
import com.nsgej.gestinapp.data.entities.UsuarioEntity
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


}