package com.nsgej.gestinapp.data.dao

import androidx.room.*
import com.nsgej.gestinapp.data.entities.CargoEntity
import com.nsgej.gestinapp.data.entities.relations.otm.CargoConUsuariosEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CargoDao {

    @Query("select * from tb_cargo")
    fun obtenerCargos() : Flow<List<CargoEntity>>

    @Query("select * from tb_cargo where id_cargo= :id")
    suspend fun obtenerCargoPorId(id: String) : CargoEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarCargo(cargo: CargoEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarCargos(cargos: List<CargoEntity>)

    @Transaction
    @Query("select * from tb_cargo ")
    suspend fun obtenerUsuariosConCargo() : List<CargoConUsuariosEntity>


    @Query("DELETE FROM tb_cargo")
    suspend fun borrarTodo()

}