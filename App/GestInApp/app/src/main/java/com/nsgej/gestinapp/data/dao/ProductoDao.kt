package com.nsgej.gestinapp.data.dao

import androidx.room.*
import com.nsgej.gestinapp.data.entities.ProductoEntity
import com.nsgej.gestinapp.data.entities.relations.otm.ProductoConInventariosEntity
import com.nsgej.gestinapp.domain.model.Producto
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductoDao {

    @Query("select * from tb_producto")
    fun obtenerProductos() : Flow<List<ProductoEntity>>

    @Query("select * from tb_producto where id_producto = :id")
    suspend fun obtenerProductoPorId(id: String) : ProductoEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarProducto(producto: ProductoEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarProductos(productos: List<ProductoEntity>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun actualizarProducto(producto : ProductoEntity)

    @Delete(entity = ProductoEntity::class)
    suspend fun eliminarProducto(producto : ProductoEntity)

    @Transaction
    @Query("select * from tb_producto where id_producto = :id")
    suspend fun obtenerProductoConInventarios(id : String) : List<ProductoConInventariosEntity>

    @Query("DELETE FROM tb_producto")
    suspend fun borrarTodo()

}