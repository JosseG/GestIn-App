package com.nsgej.gestinapp.ui.adapter.producto

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.databinding.ItemMntmProductoListaEspBinding
import com.nsgej.gestinapp.domain.model.Producto

class ProductosEspecificoViewHolder (private val binding: ItemMntmProductoListaEspBinding) : RecyclerView.ViewHolder(binding.root) {

    lateinit var imagev : ImageView

    @SuppressLint("SetTextI18n")
    fun cargarDatos(producto: Producto){
        imagev = binding.imgvProductoListItem
        //binding.imgvProductoListItem.setImageResource(R.drawable.ic_administrador)
        Glide.with(imagev.context).load(producto.imagenUrl).into(imagev)
        binding.textView4.text = producto.marca
        binding.textView5.text = producto.descripcion
        /*  binding..text = tipoProducto.nombre*/
        binding.imgvProductoListItem.transitionName = producto.id
    }
}