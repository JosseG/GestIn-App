package com.nsgej.gestinapp.ui.menu.maintenance.producto

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.databinding.FragmentMntmProductoActualizacionBinding
import com.nsgej.gestinapp.databinding.FragmentMntmProductoRegistroBinding
import com.nsgej.gestinapp.domain.model.Producto
import com.nsgej.gestinapp.viewmodel.producto.ProductoViewModel


import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MntmProductoRegistroFragment : Fragment() {

    private var _binding: FragmentMntmProductoRegistroBinding? = null
    val binding get() = _binding!!

    private val args by navArgs<MntmProductoRegistroFragmentArgs>()
    private val productoViewModel by viewModels<ProductoViewModel>()
    lateinit var productoItem : Producto

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMntmProductoRegistroBinding.inflate(inflater, container, false)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        return binding.root
    }
    lateinit var contexto: Context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        contexto = context
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnIngreso.setOnClickListener {
            binding.txtIdproducto.error=null
            binding.txtDescripcion.error = null
            binding.txtStock.error= null

            val id = binding.txtIdproducto.editText?.text.toString()
            val idvalidar = "[A-Za-z\\d\\-_\\sÑñ]{5}".toRegex()
            if(id.isEmpty()){
                binding.txtIdproducto.error ="Campo Requerido"
                return@setOnClickListener
            }
            if (!idvalidar.matches(id)) {
                binding.txtIdproducto.error = "Solo 5 caracteres"
                return@setOnClickListener
            }

            val codigoBarra = binding.txtIdproducto.editText?.text.toString()
            val Codigovalidar = "[A-Za-z\\d\\-_\\sÑñ]{8,20}".toRegex()
            if(codigoBarra.isEmpty()){
                binding.txtIdproducto.error ="Campo Requerido"
                return@setOnClickListener
            }
            if (!Codigovalidar.matches(codigoBarra)) {
                binding.txtDescripcion.error = "De 8 a 20 caracteres"
                return@setOnClickListener
            }
            //-----------------------------------------------------
            val descripcion = binding.txtDescripcion.editText?.text.toString()
            val descripcionValidar = "[A-Za-z\\d\\-_\\sÑñ]{5,70}".toRegex()
            if (descripcion.isEmpty()) {
                binding.txtDescripcion.error = "Campo Requerido"
                return@setOnClickListener
            }
            if (!descripcionValidar.matches(descripcion)) {
                binding.txtDescripcion.error = "De 5 a 70 caracteres"
                return@setOnClickListener
            }
            //-----------------------------------------------------
            val marca = binding.txtDescripcion.editText?.text.toString()
            val Marcavalidar = "[A-Za-z\\d\\-_\\sÑñ]{1,20}".toRegex()
            if (!Marcavalidar.matches(marca)) {
                binding.txtStock.error = "Campo Requerido"
                return@setOnClickListener
            }
            MaterialAlertDialogBuilder(contexto)
                .setTitle("-------------EXITO-------------")
                .setMessage("Nuevo Producto Ingresado")
                .show()
            Log.i("Resultado", "$id-> $codigoBarra-> $descripcion -> $marca")

            var producto= Producto(productoItem.id,productoItem.idTipoProducto,codigoBarra, descripcion, marca,productoItem.imagenUrl,productoItem.estado)
            productoViewModel.insertarProducto(producto)

        }

        binding.btnRegresar.setOnClickListener {
            val direction: NavDirections =
                MntmProductoRegistroFragmentDirections.actionMntmProductoRegistroFragmentToMntmProductoListaEspFragment(
                    idtipoproducto = args.idtipoproducto,
                    nombretipoproducto = args.nombretipoproducto
                )
            findNavController().navigate(direction)
        }
    }
}