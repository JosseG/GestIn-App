package com.nsgej.gestinapp.data.dao

import androidx.room.*
import com.nsgej.gestinapp.data.entities.EmpleadoEntity
import com.nsgej.gestinapp.data.entities.ProductoEntity
import com.nsgej.gestinapp.data.entities.UsuarioEntity
import com.nsgej.gestinapp.data.entities.relations.otm.EmpleadoConInventariosEntity
import com.nsgej.gestinapp.data.entities.relations.oto.EmpleadoYUsuario
import com.nsgej.gestinapp.domain.model.Empleado
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

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun actualizarEmpleado(empleado: EmpleadoEntity)

    @Delete(entity = EmpleadoEntity::class)
    suspend fun eliminarEmpleado(empleado: EmpleadoEntity)

    @Transaction
    @Query("select * from tb_empleado Where id_almacen = :almacen")
    suspend fun obtenerEmpleadosYUsuarios(almacen:String) : List<EmpleadoYUsuario>

    @Transaction
    @Query("select * from tb_empleado Where id_empleado = :id")
    suspend fun obtenerEmpleadoYUsuario(id:String) : EmpleadoYUsuario

    @Transaction
    @Query("select * from tb_empleado")
    suspend fun obtenerEmpleadoConInventarios() : List<EmpleadoConInventariosEntity>


    @Query("select * from tb_empleado order by id_empleado desc")
    suspend fun obtenerUltimoEmpleado() : EmpleadoEntity


    @Query("select * from tb_empleado where id_almacen = :idalmacen ")
    suspend fun obtenerEmpleadosXAlmacen(idalmacen: String) : List<EmpleadoEntity>


    @Query("DELETE FROM tb_empleado")
    suspend fun borrarTodo()

}