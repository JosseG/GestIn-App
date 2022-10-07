package com.nsgej.gestinapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nsgej.gestinapp.data.entities.MenuEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {

    @Query("select * from tb_menu")
    fun obtenerMenus() : Flow<List<MenuEntity>>

    @Query("select * from tb_menu where id_menu = :id")
    fun obtenerMenuPorId(id: String) : Flow<MenuEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarMenu(menu: MenuEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarMenus(menus: List<MenuEntity>)


}