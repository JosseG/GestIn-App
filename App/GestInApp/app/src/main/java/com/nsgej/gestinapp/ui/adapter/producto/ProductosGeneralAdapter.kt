package com.nsgej.gestinapp.ui.adapter.producto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nsgej.gestinapp.databinding.ItemMntmProductoListaGenBinding
import com.nsgej.gestinapp.domain.model.TipoProducto

class ProductosGeneralAdapter (val onClick: (TipoProducto) -> Unit) : RecyclerView.Adapter<ProductosGeneralViewHolder>() {

    private var lista = mutableListOf<TipoProducto>()

    fun cargarLista(list: List<TipoProducto>){
        lista.addAll(list)
        notifyDataSetChanged()
    }

    fun clean(){
        lista.clear()
    }


    lateinit var binding : ItemMntmProductoListaGenBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosGeneralViewHolder {
        binding = ItemMntmProductoListaGenBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductosGeneralViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductosGeneralViewHolder, position: Int) {

        val tipoProducto = lista[position]

        holder.cargarDatos(tipoProducto)
        holder.itemView.setOnClickListener{
            onClick(tipoProducto)
        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}