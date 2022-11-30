package com.nsgej.gestinapp.data.dataclass

data class InventarioDC(
    var codigo : Int =0,
    var idProducto: String="",
    var idTipoInventario: Int=0,
    var idAlmacen: String="",
    var idEmpleado: String="",
    var cantidad: Int=0,
    var descripcion: String = "",
    var estado: Boolean = true)  {

    override fun toString(): String {
        return idProducto
    }
}