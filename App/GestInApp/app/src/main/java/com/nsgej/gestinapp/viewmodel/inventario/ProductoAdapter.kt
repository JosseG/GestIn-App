package com.nsgej.gestinapp.viewmodel.inventario

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.domain.model.Producto

class ProductoAdapter(context: Context, produts: List<Producto>):
    ArrayAdapter<Producto>(context,0,produts) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView?: LayoutInflater.from(context).inflate(R.layout.item_combos_trsc_inventario,parent,false)
        getItem(position)?.let { lista ->
            view.findViewById<TextView>(R.id.codig).apply {
                text=lista.id
            }
            view.findViewById<TextView>(R.id.name).apply {
                text=lista.descripcion
            }
        }

        return  view
    }
}