package com.nsgej.gestinapp.data.dao

import androidx.room.*
import com.nsgej.gestinapp.data.entities.AccesoCargoEntity
import com.nsgej.gestinapp.data.entities.relations.mtm.AccesoConCargosEntity
import com.nsgej.gestinapp.data.entities.relations.mtm.CargoConAccesosEntity


@Dao
interface AccesoCargoDao {


    @Query("DELETE FROM tb_acceso_cargo")
    suspend fun borrarTodo()


    @Transaction
    @Query("SELECT * FROM tb_acceso")
    suspend fun obtenerAccesoConCargos() : List<AccesoConCargosEntity>

    @Transaction
    @Query("SELECT * FROM tb_cargo")
    suspend fun obtenerCargoConAccesos() : List<CargoConAccesosEntity>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(accesoCargo: AccesoCargoEntity)



}