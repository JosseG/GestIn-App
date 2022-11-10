package com.nsgej.gestinapp.data.repository

import com.nsgej.gestinapp.data.dao.CargoDao
import com.nsgej.gestinapp.data.entities.toEntity
import com.nsgej.gestinapp.domain.model.Cargo
import com.nsgej.gestinapp.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CargoRepositorio @Inject constructor(
    private val cargoDao: CargoDao
)  {

    fun obtenerCargos() : Flow<List<Cargo>> {
        var respuesta = cargoDao.obtenerCargos().map{
            it.map {
                    e -> e.toDomain()
            }
        };
        return respuesta
    }

    suspend fun obtenerCargo(id : String) : Cargo {
        var respuesta = cargoDao.obtenerCargoPorId(id).toDomain()

        return respuesta
    }

    suspend fun insertarCargo(cargo: Cargo ) {
        cargoDao.agregarCargo(cargo.toEntity())
    }

    suspend fun insertarCargos(cargos: List<Cargo>){
        cargoDao.agregarCargos(cargos.map { it.toEntity() })
    }


}