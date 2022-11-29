package com.nsgej.gestinapp.data.dao

import androidx.room.*
import com.nsgej.gestinapp.data.entities.ProductoAlmacenEntity
import com.nsgej.gestinapp.data.entities.relations.mtm.AlmacenConProductosEntity
import com.nsgej.gestinapp.data.entities.relations.mtm.ProductoConAlmacenesEntity
import com.nsgej.gestinapp.domain.model.ProductoAlmacen


@Dao
interface ProductoAlmacenDao {


    @Query("DELETE FROM tb_producto_almacen")
    suspend fun borrarTodo()


    @Transaction
    @Query("SELECT * FROM tb_almacen WHERE id_almacen = :id")
    suspend fun obtenerAlmacenConProductos(id : String) : List<AlmacenConProductosEntity>

    @Transaction
    @Query("SELECT * FROM tb_producto")
    suspend fun obtenerProductoConAlmacenes() : List<ProductoConAlmacenesEntity>

    @Query("SELECT * FROM tb_producto_almacen")
    suspend fun obtenerTodos() : List<ProductoAlmacenEntity>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(productoAlmacen: ProductoAlmacenEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(productosAlmacenes: List<ProductoAlmacenEntity>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(productoAlmacen: ProductoAlmacenEntity)

}