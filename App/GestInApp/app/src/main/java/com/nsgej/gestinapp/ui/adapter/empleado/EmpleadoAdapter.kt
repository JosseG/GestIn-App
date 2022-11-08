package com.nsgej.gestinapp.ui.adapter.empleado

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nsgej.gestinapp.databinding.ItemMntmEmpleadoBinding
import com.nsgej.gestinapp.domain.model.Empleado

class EmpleadoAdapter(val onClick: (Empleado) -> Unit) : RecyclerView.Adapter<EmpleadosViewHolder>() {

    private var lista = mutableListOf<Empleado>()

    fun cargarLista(list: List<Empleado>){
        lista.addAll(list)
        notifyDataSetChanged()
    }

    fun clean(){
        lista.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpleadosViewHolder {
        val binding = ItemMntmEmpleadoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return EmpleadosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmpleadosViewHolder, position: Int) {
        val empleado = lista[position]
        holder.cargarDatos(empleado)
        holder.itemView.setOnClickListener{

            onClick(empleado)
        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }


}