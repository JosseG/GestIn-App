package com.nsgej.gestinapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nsgej.gestinapp.data.entities.EmpleadoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EmpleadoDao {

    @Query("select * from tb_empleado")
    fun obtenerEmpleados() : Flow<List<EmpleadoEntity>>

    @Query("select * from tb_empleado where id_empleado= :id")
    fun obtenerEmpleadoPorId(id: String) : Flow<EmpleadoEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarEmpleado(empleado: EmpleadoEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarEmpleados(empleados: List<EmpleadoEntity>)

}