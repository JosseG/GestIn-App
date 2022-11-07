package com.nsgej.gestinapp.data.dao

import androidx.room.*
import com.nsgej.gestinapp.data.entities.MenuEntity
import com.nsgej.gestinapp.data.entities.ProductoEntity
import com.nsgej.gestinapp.data.entities.relations.otm.EmpleadoConInventariosEntity
import com.nsgej.gestinapp.data.entities.relations.otm.ProductoConInventariosEntity
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

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun actualizarProducto(producto : ProductoEntity)

    @Transaction
    @Query("select * from tb_producto where id_producto = :id")
    suspend fun obtenerProductoConInventarios(id : String) : List<ProductoConInventariosEntity>

    @Query("DELETE FROM tb_producto")
    suspend fun borrarTodo()

}