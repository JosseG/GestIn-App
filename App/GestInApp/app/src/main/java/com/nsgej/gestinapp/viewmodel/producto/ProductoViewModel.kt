package com.nsgej.gestinapp.viewmodel.producto

import android.util.Log
import androidx.lifecycle.*
import com.nsgej.gestinapp.data.entities.ProductoEntity
import com.nsgej.gestinapp.data.entities.relations.otm.TipoProductoConProductosEntity
import com.nsgej.gestinapp.data.repository.ProductoAlmacenRepositorio
import com.nsgej.gestinapp.data.repository.ProductoRepositorio
import com.nsgej.gestinapp.data.repository.TipoProductoRepositorio
import com.nsgej.gestinapp.domain.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductoViewModel @Inject constructor(
    private val productoRepositorio: ProductoRepositorio,
    private val tipoProductoRepositorio: TipoProductoRepositorio,
    private val productoAlmacenRepositorio: ProductoAlmacenRepositorio
) : ViewModel() {


    val productos = productoRepositorio.obtenerProductos().asLiveData()

    val tiposProducto = tipoProductoRepositorio.obtenerTipoProductos().asLiveData()


    private var _productoPorTipoObtenido = MutableLiveData<Producto>()
    val productoPorTipoObtenido: LiveData<Producto> = _productoPorTipoObtenido


    private var _productosPorTipoObtenido = MutableLiveData<TipoProductoConProductosEntity>()
    val productosPorTipoObtenido: LiveData<TipoProductoConProductosEntity> =
        _productosPorTipoObtenido

    private var _productosPorAlmacenObtenido = MutableLiveData<List<AlmacenConProductos>>()
    val productosPorAlmacenObtenido: LiveData<List<AlmacenConProductos>> =
        _productosPorAlmacenObtenido

    private var _productosFiltradoPorAlmacenYTipo = MutableLiveData<List<ProductoAlmacen>>()
    val productosFiltradoPorAlmacenYTipo: LiveData<List<ProductoAlmacen>> =
        _productosFiltradoPorAlmacenYTipo


    private var _productosFiltradoPorAlmacenYTipoMapping =
        MutableLiveData<HashMap<Producto, ProductoAlmacen>>()
    val productosFiltradoPorAlmacenYTipoMapping: LiveData<HashMap<Producto, ProductoAlmacen>> =
        _productosFiltradoPorAlmacenYTipoMapping


    fun insertarProducto(producto: Producto) {
        viewModelScope.launch {
            productoRepositorio.insertPoducto(producto)
        }
    }

    fun insertarProductos(producto: List<Producto>) {
        viewModelScope.launch {
            productoRepositorio.insertarPoductos(producto)
        }
    }

    fun insertarTipoProducto(tipoProducto: TipoProducto) {
        viewModelScope.launch {
            tipoProductoRepositorio.insertarTipoProducto(tipoProducto)
        }
    }

    fun insertarTiposProducto(tiposProducto: List<TipoProducto>) {
        viewModelScope.launch {
            tipoProductoRepositorio.insertarTiposProducto(tiposProducto)
        }
    }


    fun actualizar(producto: Producto) {
        viewModelScope.launch {
            productoRepositorio.actualizarProducto(producto)
        }
    }

    fun obtenerProducto(id: String) {
        viewModelScope.launch {
            _productoPorTipoObtenido.value = productoRepositorio.obtenerProducto(id)
        }
    }

    fun eliminar(producto: Producto) {
        viewModelScope.launch {

        }
    }

    fun obtenerProductosPorTipoProducto(id: Int) {

        viewModelScope.launch {
            _productosPorTipoObtenido.value =
                tipoProductoRepositorio.obtenerProductosPorTipoProducto(id)
        }

    }

    fun obtenerProductosPorTipoProductoDelXAlmacen(idAlmacen: String, idTipo: Int) {

        viewModelScope.launch {

            val productosSoloFiltroAlmacen =
                productoAlmacenRepositorio.obtenerProductosPorAlmacen(idAlmacen)

            val productostipoFiltrando: MutableList<ProductoEntity> = mutableListOf()

            val todosProductoAlmacen = productoAlmacenRepositorio.obtenerTodos()

            val productosfiltradoAlmacenYTipo: HashMap<Producto, ProductoAlmacen> = HashMap()

            productosSoloFiltroAlmacen.forEach { almprod ->
                almprod.productos.forEach foreachalm@{ productos ->
                    todosProductoAlmacen.forEach { productosalmacen ->
                        if (productos.idTipoProducto == idTipo) {
                            productostipoFiltrando.add(productos)
                            if (productosalmacen.idProducto == productos.id) {
                                Log.i("Ingreso ", productos.id + " " + productosalmacen.idProducto)
                                productosfiltradoAlmacenYTipo[productos.toDomain()] =
                                    productosalmacen
                                return@foreachalm
                            }

                        }

                    }

                }
            }
            _productosFiltradoPorAlmacenYTipoMapping.value = productosfiltradoAlmacenYTipo
        }

    }

    fun obtenerProductosPorAlmacen(id: String) {

        viewModelScope.launch {
            _productosPorAlmacenObtenido.value =
                productoAlmacenRepositorio.obtenerProductosPorAlmacen(id)

        }

    }


    fun insertarProductoAlmacen(productoAlmacen: ProductoAlmacen) {
        viewModelScope.launch {
            productoAlmacenRepositorio.insertarProductoAlmacen(productoAlmacen)
        }
    }

    fun insertarProductosAlmacenes(productosAlmacenes: List<ProductoAlmacen>) {
        viewModelScope.launch {
            productoAlmacenRepositorio.insertarProductosAlmacenes(productosAlmacenes)
        }
    }


}