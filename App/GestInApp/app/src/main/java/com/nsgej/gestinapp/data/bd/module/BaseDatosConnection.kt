package com.nsgej.gestinapp.data.bd.module

import android.content.Context
import androidx.room.Room
import com.nsgej.gestinapp.data.bd.BaseDatos
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object BaseDatosConnection {

    @Singleton
    @Provides
    fun getDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, BaseDatos::class.java,"bd_almacen").build()

    @Singleton
    @Provides
    fun provideEmpleadoDao(db:BaseDatos) = db.getEmpleadoDao()

    @Singleton
    @Provides
    fun provideUsuarioDao(db:BaseDatos) = db.getUsuarioDao()

    @Singleton
    @Provides
    fun provideCargoDao(db:BaseDatos) = db.getCargoDao()

    @Singleton
    @Provides
    fun provideSucursalDao(db:BaseDatos) = db.getSucursalDao()

    @Singleton
    @Provides
    fun provideAlmacenDao(db:BaseDatos) = db.getAlmacenDao()

}