package com.nsgej.gestinapp.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.nsgej.gestinapp.data.dao.InventarioDao
import com.nsgej.gestinapp.data.dataclass.InventarioDC
import com.nsgej.gestinapp.data.entities.toEntity
import com.nsgej.gestinapp.domain.model.Inventario
import com.nsgej.gestinapp.domain.model.toDomain
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.properties.Delegates

class InventarioRepositorio @Inject constructor(private val objDaoInv: InventarioDao) {

    val db = Firebase.firestore

    fun listarTodoinventario(): Flow<List<Inventario>> {
        var respuesta = objDaoInv.obtenerInventarios().map {
            it.map { e ->
                e.toDomain()
            }
        };
        return respuesta
    }

    suspend fun insertInventario(inventario: Inventario) {
        objDaoInv.agregarInventario(inventario.toEntity())
    }

    suspend fun updateInventario(inventario: Inventario) {
        objDaoInv.actualizarInventario(inventario.toEntity())
    }

    suspend fun deleteInventario(cod: String) {
        objDaoInv.eliminarInventarioPorId(cod)
    }

    //métodos Firebase

    fun insertInventarioFirebase(inventario: InventarioDC) {
        db.collection("Inventario").add(inventario)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "Inventario agregado con IdDocumento: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error en adicionar el  inventario", e)
            }
    }

    suspend fun listarTodoinventarioFirebase(almacen : String): MutableList<InventarioDC?> {

        val listaInventario :  MutableList<InventarioDC?>  = mutableListOf<InventarioDC?>()

        val query = db.collection("Inventario")

        val snapshot = query.whereEqualTo("codigo", almacen).get().await()
        for(document in snapshot.documents){

            listaInventario.add(document.toObject())
        }

        return listaInventario
    }


    suspend fun listarUltimoInventionFirebase(): InventarioDC? {
        var inventarioToModel by Delegates.notNull<Int>()
        val query = db.collection("Inventario")
            .orderBy("codigo", Query.Direction.DESCENDING)
            .limit(1)


                val snapshot = query.get().await()
                var documentsnapshot : InventarioDC? = InventarioDC()

                for(document in snapshot.documents){
                    documentsnapshot = document.toObject()
                }
/*
                if(snapshot.documents.lastIndex!=0){
                    return snapshot.documents.get(0).toObject(InventarioDC::class.java)
                }*/

                return documentsnapshot

            /*addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w(TAG, "listen:error", e)
                    return@addSnapshotListener
                }

                for (dc in snapshots!!.documentChanges) {
                     when (dc.type) {
                        DocumentChange.Type.ADDED -> {
                            Log.d("Nuevo registro:", "${dc.document.data}")
                            val inventario: InventarioDC = dc.document.toObject()

                             inventarioToModel = inventario.cantidad



                        }
                        DocumentChange.Type.MODIFIED -> {
                            Log.d("Registro modificado:", " ${dc.document.data}")
                            val inventario: InventarioDC = dc.document.toObject()

                            inventarioToModel = inventario.cantidad

                        }
                        DocumentChange.Type.REMOVED -> {
                            Log.d("registro eliminado:", " ${dc.document.data}")
                            val inventario: InventarioDC = dc.document.toObject()

                            inventarioToModel = inventario.cantidad

                        }

                    }
                }

            }*/

    }

}