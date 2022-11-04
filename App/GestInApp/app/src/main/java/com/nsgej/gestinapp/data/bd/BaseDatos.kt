package com.nsgej.gestinapp.data.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nsgej.gestinapp.data.dao.*
import com.nsgej.gestinapp.data.entities.*
import com.nsgej.gestinapp.domain.model.AccesoCargo
import com.nsgej.gestinapp.domain.model.Almacen
import com.nsgej.gestinapp.domain.model.Sucursal

@Database(
    entities =
    [
        AccesoEntity::class,
        AccesoCargoEntity::class,
        AlmacenEntity::class,
        CargoEntity::class,
        EmpleadoEntity::class,
        InventarioEntity::class,
        MenuEntity::class,
        ProductoEntity::class,
        SucursalEntity::class,
        TipoInventarioEntity::class,
        TipoProductoEntity::class,
        UsuarioEntity::class
    ],
    version = 1
)
abstract class BaseDatos : RoomDatabase() {

    abstract fun getEmpleadoDao(): EmpleadoDao
    abstract fun getAccesoDao(): AccesoDao
    abstract fun getAccesoCargoDao(): AccesoCargoDao
    abstract fun getAlmacenDao(): AlmacenDao
    abstract fun getCargoDao(): CargoDao
    abstract fun getUsuarioDao(): UsuarioDao
    abstract fun getSucursalDao(): SucursalDao

}