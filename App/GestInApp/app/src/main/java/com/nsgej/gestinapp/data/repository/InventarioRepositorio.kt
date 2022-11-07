package com.nsgej.gestinapp.data.repository

import com.nsgej.gestinapp.data.dao.InventarioDao
import com.nsgej.gestinapp.data.entities.InventarioEntity
import com.nsgej.gestinapp.data.entities.toEntity
import com.nsgej.gestinapp.domain.model.Inventario
import com.nsgej.gestinapp.domain.model.Producto
import com.nsgej.gestinapp.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class InventarioRepositorio @Inject constructor(private val objDaoInv: InventarioDao) {



    fun listarTodoinventario() : Flow<List<Inventario>> {
        var respuesta = objDaoInv.obtenerInventarios().map{
            it.map {
                    e -> e.toDomain()
            }
        };
        return respuesta
    }

    suspend fun insertInventario(inventario: Inventario){
        objDaoInv.agregarInventario(inventario.toEntity())
    }

    suspend fun updateInventario(inventario: Inventario){
        objDaoInv.actualizarInventario(inventario.toEntity())
    }

    suspend fun deleteInventario(cod:String){
        objDaoInv.eliminarInventarioPorId(cod)
    }
}