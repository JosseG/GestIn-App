package com.nsgej.gestinapp.ui.adapter.empleado

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.databinding.ItemMntmEmpleadoBinding
import com.nsgej.gestinapp.domain.model.Empleado

class EmpleadosViewHolder (private val binding: ItemMntmEmpleadoBinding) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun cargarDatos(empleado: Empleado){

        binding.imageView.setImageResource(R.drawable.ic_administrador)
        binding.textView4.text = empleado.nombre

    }

}