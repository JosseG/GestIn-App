package com.nsgej.gestinapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nsgej.gestinapp.data.entities.MenuEntity
import com.nsgej.gestinapp.data.entities.ProductoEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductoDao {

    @Query("select * from tb_producto")
    fun obtenerProductos() : Flow<List<ProductoEntity>>

    @Query("select * from tb_producto where id_producto = :id")
    fun obtenerProductoPorId(id: String) : Flow<ProductoEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarProducto(producto: ProductoEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarProductos(productos: List<ProductoEntity>)


}