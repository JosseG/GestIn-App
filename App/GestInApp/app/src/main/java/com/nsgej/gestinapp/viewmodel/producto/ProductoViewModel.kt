package com.nsgej.gestinapp.viewmodel.producto

import androidx.lifecycle.*
import com.nsgej.gestinapp.data.entities.relations.otm.TipoProductoConProductosEntity
import com.nsgej.gestinapp.data.repository.ProductoRepositorio
import com.nsgej.gestinapp.data.repository.TipoProductoRepositorio
import com.nsgej.gestinapp.domain.model.Producto
import com.nsgej.gestinapp.domain.model.TipoProducto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductoViewModel @Inject constructor(private val productoRepositorio: ProductoRepositorio ,private val tipoProductoRepositorio: TipoProductoRepositorio) : ViewModel() {


    val productos  = productoRepositorio.obtenerProductos().asLiveData();

    val tiposProducto  = tipoProductoRepositorio.obtenerTipoProductos().asLiveData();


    private var _productoPorTipoObtenido = MutableLiveData<Producto>()
    val productoPorTipoObtenido: LiveData<Producto> = _productoPorTipoObtenido


    private var _productosPorTipoObtenido = MutableLiveData<TipoProductoConProductosEntity>()
    val productosPorTipoObtenido : LiveData<TipoProductoConProductosEntity> = _productosPorTipoObtenido


    fun insertarProducto(producto: Producto){
        viewModelScope.launch {
            productoRepositorio.insertPoducto(producto)
        }
    }

    fun insertarProductos(producto: List<Producto>){
        viewModelScope.launch {
            productoRepositorio.insertarPoductos(producto)
        }
    }

    fun insertarTipoProducto(tipoProducto: TipoProducto){
        viewModelScope.launch {
            tipoProductoRepositorio.insertarTipoProducto(tipoProducto)
        }
    }

    fun insertarTiposProducto(tiposProducto: List<TipoProducto>){
        viewModelScope.launch {
            tipoProductoRepositorio.insertarTiposProducto(tiposProducto)
        }
    }


    fun actualizar(producto: Producto){
        viewModelScope.launch {
            productoRepositorio.actualizarProducto(producto)
        }
    }
    fun eliminar(producto: Producto) {
        viewModelScope.launch {
            productoRepositorio.eliminarProducto(producto)
        }
    }

    fun obtenerProducto(id : String ){
        viewModelScope.launch {
            _productoPorTipoObtenido.value =  productoRepositorio.obtenerProducto(id)
        }
    }


    fun obtenerProductosPorTipoProducto(id : String){

        viewModelScope.launch {
            _productosPorTipoObtenido.value = tipoProductoRepositorio.obtenerProductosPorTipoProducto(id)
        }

    }



}