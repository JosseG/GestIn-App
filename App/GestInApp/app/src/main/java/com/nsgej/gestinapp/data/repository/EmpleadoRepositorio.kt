package com.nsgej.gestinapp.data.repository

import com.nsgej.gestinapp.data.dao.EmpleadoDao
import com.nsgej.gestinapp.data.entities.relations.mtm.AlmacenConProductosEntity
import com.nsgej.gestinapp.data.entities.toEntity
import com.nsgej.gestinapp.domain.dto.Worker
import com.nsgej.gestinapp.domain.dto.toDomain
import com.nsgej.gestinapp.domain.model.Empleado
import com.nsgej.gestinapp.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EmpleadoRepositorio @Inject constructor(
    private val empleadoDao: EmpleadoDao
) {

    fun obtenerEmpleados() : Flow<List<Empleado>> {
        var respuesta = empleadoDao.obtenerEmpleados().map{
            it.map {
                    e -> e.toDomain()
            }
        };
        return respuesta
    }

    suspend fun obtenerEmpleado(id:String) : Empleado {
        var respuesta = empleadoDao.obtenerEmpleadoPorId(id).toDomain()
        return respuesta
    }

    suspend fun eliminarEmpleado(empleado: Empleado) {
        empleadoDao.eliminarEmpleado(empleado.toEntity())
    }

    suspend fun obtenerEmpleadoYUsuario(id:String) : Worker {
        var respuesta = empleadoDao.obtenerEmpleadoYUsuario(id).toDomain()
        return respuesta
    }

    suspend fun obtenerUltimoEmpleado() : Empleado {
        val respuesta = empleadoDao.obtenerUltimoEmpleado().toDomain()
        return respuesta
    }

    suspend fun obtenerEmpleadosXAlmacen(almacen: String): List<Empleado> {
        val respuesta = empleadoDao.obtenerEmpleadosXAlmacen(almacen).map { it.toDomain() }
        return respuesta
    }

    suspend fun obtenerEmpleadosYUsuariosXAlmacen(almacen: String) : List<Worker> {
        val respuesta = empleadoDao.obtenerEmpleadosYUsuarios(almacen).map {ent -> ent.toDomain()}
        return respuesta
    }

    suspend fun insertarEmpleado(empleado : Empleado) {
        empleadoDao.agregarEmpleado(empleado.toEntity())
    }

    suspend fun actualizarEmpleado(empleado: Empleado){
        empleadoDao.actualizarEmpleado(empleado.toEntity())
    }

    suspend fun insertarEmpleados(empleado : List<Empleado>) {
        empleadoDao.agregarEmpleados(empleado.map { it.toEntity() })
    }


}