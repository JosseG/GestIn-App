package com.nsgej.gestinapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nsgej.gestinapp.data.entities.CargoEntity
import com.nsgej.gestinapp.data.entities.EmpleadoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CargoDao {

    @Query("select * from tb_cargo")
    fun obtenerCargos() : Flow<List<CargoEntity>>

    @Query("select * from tb_cargo where id_cargo= :id")
    fun obtenerCargoPorId(id: String) : Flow<CargoEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarCargo(cargo: CargoEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarCargos(cargos: List<CargoEntity>)

}