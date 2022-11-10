package com.nsgej.gestinapp.data.repository

import com.nsgej.gestinapp.data.dao.TipoInventarioDao
import com.nsgej.gestinapp.data.entities.toEntity
import com.nsgej.gestinapp.domain.model.TipoInventario
import com.nsgej.gestinapp.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TipoInventarioRepositorio @Inject constructor(private  val objTipoInv: TipoInventarioDao) {



    fun ListarTipoinventario() : Flow<List<TipoInventario>> {
        var respuesta = objTipoInv.obtenerTiposInventario().map{
            it.map {
                    e -> e.toDomain()
            }
        };
        return respuesta
    }


    suspend fun insertTipoInventario(tipoInventario: TipoInventario){
        objTipoInv.agregarTipoInventario(tipoInventario.toEntity())
    }
}