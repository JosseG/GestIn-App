package com.nsgej.gestinapp.viewmodel.producto

import android.util.Log
import androidx.lifecycle.*
import com.nsgej.gestinapp.data.entities.relations.otm.TipoProductoConProductosEntity
import com.nsgej.gestinapp.data.repository.ProductoAlmacenRepositorio
import com.nsgej.gestinapp.data.repository.ProductoRepositorio
import com.nsgej.gestinapp.data.repository.TipoProductoRepositorio
import com.nsgej.gestinapp.domain.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
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

    private var _productosFueraDeAlmacenPorTipo = MutableLiveData<List<Producto>>()
    val productosFueraDeAlmacenPorTipo: LiveData<List<Producto>> = _productosFueraDeAlmacenPorTipo


    private var _listarProductosXMiAlmacen = MutableLiveData<List<Producto>>()
    val listarProductosXMiAlmacen: LiveData<List<Producto>> = _listarProductosXMiAlmacen


    private var _listarProductosXMiAlmacenV2 = MutableLiveData<List<Producto>>()
    val listarProductosXMiAlmacenV2: LiveData<List<Producto>> = _listarProductosXMiAlmacenV2

    private var _productoAlmacen = MutableLiveData<ProductoAlmacen>()
    val productoAlmacen : LiveData<ProductoAlmacen> = _productoAlmacen




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

            /*val productostipoFiltrando: MutableList<ProductoEntity> = mutableListOf()*/

            val todosProductoAlmacen = productoAlmacenRepositorio.obtenerTodos()

            val productosfiltradoAlmacenYTipo: HashMap<Producto, ProductoAlmacen> = HashMap()

            productosSoloFiltroAlmacen.forEach { almprod ->
                almprod.productos.forEach foreachalm@{ productos ->
                    todosProductoAlmacen.forEach { productosalmacen ->
                        if (productos.idTipoProducto == idTipo) {
//                            productostipoFiltrando.add(productos)
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


    fun obtenerProductosPorTipoProductoFueraDeAlmacen(idAlmacen: String, idTipo: Int) {

        viewModelScope.launch {
            Log.i("LLEGO", "it.descripcion")

            val productos = productoRepositorio.obtenerTodosProductos()

            val productosSoloFiltroAlmacen =
                productoAlmacenRepositorio.obtenerProductosPorAlmacen(idAlmacen)

            productos.forEach {
                Log.i("TAG2", it.descripcion)
            }


            productosSoloFiltroAlmacen.forEach { almprod ->
                almprod.productos.forEach {
                    Log.i("Sin filtro desde la app", it.descripcion)
                }

            }
            productosSoloFiltroAlmacen.forEach { almprod ->

                var alm = almprod.productos
                var x = alm.map {
                    it.toDomain()
                }


                var el = productos.filterNotIn(x)
                el.forEach {
                    /*Log.i("Con filtro", it.descripcion)*/
                }

                val sum = productos + x

                val lista = sum.groupBy { it.id }
                    .filter { it.value.size == 1 }
                    .flatMap { it.value }

                lista.forEach {
                    Log.i("Con filtro mejorado", it.descripcion)
                }

            }


            var listaMutableProductoSinAlmacen: MutableList<Producto> = mutableListOf()

            productosSoloFiltroAlmacen.forEach { almprod ->
                var alm = almprod.productos
                var x = alm.map {
                    it.toDomain()
                }

                val sum = productos + x
                sum.groupBy { it.id }
                    .filter { it.value.size == 1 }
                    .flatMap { it.value }.forEach { producto ->
                        if (producto.idTipoProducto == idTipo) {
                            Log.i("ENpRODUCTOVIWE", producto.descripcion)
                            listaMutableProductoSinAlmacen.add(producto)

                        }

                    }
                /*almprod.productos.forEach foreachalm@{ productos ->
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

                }*/
            }

            listaMutableProductoSinAlmacen.forEach {
                Log.i("Lista sin almacen prod", it.descripcion)
            }
            _productosFueraDeAlmacenPorTipo.value = listaMutableProductoSinAlmacen
        }

    }


    suspend fun <T> Flow<List<T>>.flattenToList() = flatMapConcat { it.asFlow() }.toList()

    fun <T> Collection<T>.filterNotIn(collection: Collection<T>): Collection<T> {
        val set = collection.toSet()
        return filterNot { set.contains(it) }
    }


    fun obtenerProductosPorAlmacen(id: String) {

        viewModelScope.launch {

            val productosSoloFiltroAlmacen = productoAlmacenRepositorio.obtenerProductosPorAlmacen(id)

            productosSoloFiltroAlmacen.forEach { almprod ->
                var alm = almprod.productos
                var x = alm.map {
                    it.toDomain()
                }
                _listarProductosXMiAlmacen.value =x
            }


        }

    }


    fun obtenerProductosPorAlmacenV2(id: String) {

        viewModelScope.launch {

            val productosSoloFiltroAlmacen = productoAlmacenRepositorio.obtenerProductosPorAlmacen(id)

            productosSoloFiltroAlmacen.forEach { almprod ->
                var alm = almprod.productos
                var x = alm.map {
                    it.toDomain()
                }
                _listarProductosXMiAlmacenV2.value =x
            }


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

    fun eliminarProductoAlmacen(productoAlmacen: ProductoAlmacen){
        viewModelScope.launch {
            productoAlmacenRepositorio.eliminarProductoPorAlmacen(productoAlmacen)
        }
    }

    fun actualizarProductoAlmacen(productoAlmacen: ProductoAlmacen){
        viewModelScope.launch {
            productoAlmacenRepositorio.actualizarProductoPorAlmacen(productoAlmacen)
        }
    }


    fun obtenerProductoAlmacen(idprod : String, idAlmacen: String){
        viewModelScope.launch {


            val productoalmacen = productoAlmacenRepositorio.obtenerProdAlmacen(idprod,idAlmacen)
            Log.i("PRODUCTOOOOOOOO", productoalmacen.idProducto)

            _productoAlmacen.value = productoalmacen
        }
    }


}