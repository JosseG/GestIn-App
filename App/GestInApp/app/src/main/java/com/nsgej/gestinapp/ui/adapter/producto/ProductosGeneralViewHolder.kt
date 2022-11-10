package com.nsgej.gestinapp.ui.adapter.producto

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nsgej.gestinapp.databinding.ItemMntmProductoListaGenBinding
import com.nsgej.gestinapp.domain.model.TipoProducto

class ProductosGeneralViewHolder (private val binding: ItemMntmProductoListaGenBinding) : RecyclerView.ViewHolder(binding.root) {

    lateinit var imagev : ImageView

    @SuppressLint("SetTextI18n")
    fun cargarDatos(tipoProducto: TipoProducto){
        imagev = binding.imgvTipoProducto
        Glide.with(imagev.context).load(tipoProducto.imagenUrl).into(imagev)
        binding.lblTipoProducto.text = tipoProducto.nombre
        binding.imgvTipoProducto.transitionName = tipoProducto.id.toString()
    }

}