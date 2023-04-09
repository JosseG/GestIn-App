package com.nsgej.gestinapp.ui.adapter.empleado

import android.annotation.SuppressLint
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.databinding.ItemMntmEmpleadoBinding
import com.nsgej.gestinapp.domain.dto.Worker
import com.nsgej.gestinapp.domain.model.Empleado

class EmpleadosViewHolder (private val binding: ItemMntmEmpleadoBinding) : RecyclerView.ViewHolder(binding.root) {

    lateinit var imagev : ImageView

    @SuppressLint("SetTextI18n")
    fun cargarDatos(worker: Worker){
        imagev = binding.imageView
        Log.i("CARGOOO",worker.usuario.idCargo)
        Log.i("alias",worker.usuario.alias)
        Log.i("id",worker.empleado.id)
        when(worker.usuario.idCargo) {
            "C00001" -> binding.imageView.setImageResource(R.drawable.ic_administrador)
            "C00002" -> binding.imageView.setImageResource(R.drawable.ic_asistente_ventas)
            "C00003" -> binding.imageView.setImageResource(R.drawable.ic_almacenero)
            else -> binding.imageView.setImageResource(R.drawable.ic_administrador)
        }
        binding.textView4.text = worker.empleado.nombre
        binding.imageView.transitionName = worker.empleado.id

    }

}
