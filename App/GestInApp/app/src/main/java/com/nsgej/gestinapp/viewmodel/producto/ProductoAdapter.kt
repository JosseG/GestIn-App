package com.nsgej.gestinapp.viewmodel.producto

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.domain.model.Producto

class ProductoAdapter(context: Context, tipos: List<Producto>):
    ArrayAdapter<Producto>(context,0,tipos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView?: LayoutInflater.from(context).inflate(R.layout.item_combo_mntm_producto_ingreso,parent,false)
        getItem(position)?.let { lista ->
            view.findViewById<TextView>(R.id.codig).apply {
                text=lista.descripcion
            }
        }

        return  view
    }

}