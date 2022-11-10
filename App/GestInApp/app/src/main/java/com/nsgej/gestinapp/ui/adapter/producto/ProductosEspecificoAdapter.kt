package com.nsgej.gestinapp.ui.adapter.producto

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nsgej.gestinapp.databinding.ItemMntmProductoListaEspBinding
import com.nsgej.gestinapp.domain.model.Producto

class ProductosEspecificoAdapter (val onClick: (Producto, ImageView) -> Unit) : RecyclerView.Adapter<ProductosEspecificoViewHolder>() {

    private var lista = mutableListOf<Producto>()

    fun cargarLista(list: List<Producto>){
        lista.addAll(list)
        notifyDataSetChanged()
    }

    fun clean(){
        lista.clear()
    }

    lateinit var binding: ItemMntmProductoListaEspBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductosEspecificoViewHolder {
        binding = ItemMntmProductoListaEspBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductosEspecificoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductosEspecificoViewHolder, position: Int) {
        val producto = lista[position]
        holder.cargarDatos(producto)
        holder.itemView.setOnClickListener{
            onClick(producto,holder.imagev)
        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}