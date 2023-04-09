package com.nsgej.gestinapp.ui.adapter.empleado

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nsgej.gestinapp.databinding.ItemMntmEmpleadoBinding
import com.nsgej.gestinapp.domain.dto.Worker
import com.nsgej.gestinapp.domain.model.Empleado

class EmpleadoAdapter(val onClick: (Worker,ImageView) -> Unit) : RecyclerView.Adapter<EmpleadosViewHolder>() {

    private var lista = mutableListOf<Worker>()


    fun cargarLista(list: List<Worker>){
        lista.addAll(list)
        notifyDataSetChanged()
    }

    fun clean(){
        lista.clear()
    }

    lateinit var binding: ItemMntmEmpleadoBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpleadosViewHolder {
        binding = ItemMntmEmpleadoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return EmpleadosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmpleadosViewHolder, position: Int) {
        val empleado = lista[position]

        holder.cargarDatos(empleado)
        holder.itemView.setOnClickListener{
            onClick(empleado,holder.imagev)
        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }


}