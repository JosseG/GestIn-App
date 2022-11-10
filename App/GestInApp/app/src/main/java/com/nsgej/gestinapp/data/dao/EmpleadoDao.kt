package com.nsgej.gestinapp.data.dao

import androidx.room.*
import com.nsgej.gestinapp.data.entities.EmpleadoEntity
import com.nsgej.gestinapp.data.entities.relations.otm.EmpleadoConInventariosEntity
import com.nsgej.gestinapp.data.entities.relations.oto.EmpleadoYUsuario
import kotlinx.coroutines.flow.Flow

@Dao
interface EmpleadoDao {

    @Query("select * from tb_empleado")
    fun obtenerEmpleados() : Flow<List<EmpleadoEntity>>

    @Query("select * from tb_empleado where id_empleado = :id")
    suspend fun obtenerEmpleadoPorId(id: String) : EmpleadoEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarEmpleado(empleado: EmpleadoEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarEmpleados(empleados: List<EmpleadoEntity>)

    @Transaction
    @Query("select * from tb_empleado")
    suspend fun obtenerEmpleadoYUsuarioPorIdEmpleado() : List<EmpleadoYUsuario>

    @Transaction
    @Query("select * from tb_empleado")
    suspend fun obtenerEmpleadoConInventarios() : List<EmpleadoConInventariosEntity>


    @Query("DELETE FROM tb_empleado")
    suspend fun borrarTodo()

}