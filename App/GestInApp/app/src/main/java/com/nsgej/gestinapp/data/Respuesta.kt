package com.nsgej.gestinapp.data


sealed class Respuesta<out T : Any> {

    data class Success<out T : Any>(val data: T) : Respuesta<T>()
    data class Error(val exception: Exception) : Respuesta<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}