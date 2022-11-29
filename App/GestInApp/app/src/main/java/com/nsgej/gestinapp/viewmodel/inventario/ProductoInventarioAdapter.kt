package com.nsgej.gestinapp.viewmodel.inventario

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.data.dataclass.InventarioDC
import com.nsgej.gestinapp.domain.model.Inventario

class ProductoInventarioAdapter(context: Context, products: List<InventarioDC>):
    ArrayAdapter<InventarioDC>(context,0,products)  {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView?: LayoutInflater.from(context).inflate(R.layout.item_combos_trsc_inventario,parent,false)
        getItem(position)?.let { lista ->
            view.findViewById<TextView>(R.id.codig).apply {
                text= lista.idProducto
            }

        }

        return  view
    }


}