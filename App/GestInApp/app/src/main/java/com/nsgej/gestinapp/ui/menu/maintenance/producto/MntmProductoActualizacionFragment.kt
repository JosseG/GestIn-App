package com.nsgej.gestinapp.ui.menu.maintenance.producto

import android.os.Bundle
import android.text.Editable
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
import com.bumptech.glide.Glide
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.databinding.FragmentMntmProductoActualizacionBinding
import com.nsgej.gestinapp.domain.model.Producto
import com.nsgej.gestinapp.ui.adapter.producto.ProductosEspecificoAdapter
import com.nsgej.gestinapp.ui.adapter.producto.ProductosEspecificoViewHolder
import com.nsgej.gestinapp.viewmodel.producto.ProductoViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class MntmProductoActualizacionFragment : Fragment() {

    private var _binding: FragmentMntmProductoActualizacionBinding? = null
    val binding get() = _binding!!

    private val args by navArgs<MntmProductoActualizacionFragmentArgs>()
    //private val productoViewModel by viewModels<ProductoViewModel>()

//------------------------------------------------------------
    /*val productoViewModel by viewModels<ProductoViewModel> {
        val productopru = requireActivity().application as MiAplicacion
       viewModelFactory { productopru}
    }*/
//-------------------------------------------------------------

    private val productoViewModel by viewModels<ProductoViewModel>()
    lateinit var productoItem : Producto
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMntmProductoActualizacionBinding.inflate(inflater, container, false)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        productoViewModel.obtenerProducto(args.idproducto!!)
        productoViewModel.productoPorTipoObtenido.observe(viewLifecycleOwner){ producto ->

            productoItem = producto

            binding.txtCodBarra.editText?.text=Editable.Factory.getInstance().newEditable(producto.codigoBarra)
            binding.txtDescripcion.editText?.text=Editable.Factory.getInstance().newEditable(producto.descripcion)
            binding.txtMarca.editText?.text=Editable.Factory.getInstance().newEditable(producto.marca)
            //preguntar como pasar la imagen-----------------------------------
            /*Log.i("id",args.imagen.toString())*/
            binding.imgvProducto.transitionName = args.imagen
            Glide.with(this).load(producto.imagenUrl).into(binding.imgvProducto)

        }



        //--------------------------------------------------------------
        binding.btnActualizar.setOnClickListener {
            binding.txtCodBarra.error = null
            binding.txtDescripcion.error = null
            binding.txtMarca.error = null

            val codigoBarra = binding.txtCodBarra.editText?.text.toString()
            val Codigovalidar = "[A-Za-z\\d\\-_\\sÑñ]{1,50}".toRegex()
            if (!Codigovalidar.matches(codigoBarra)) {
                binding.txtCodBarra.error = "Campo Solicitado"
                return@setOnClickListener
            }
            val descripcion = binding.txtDescripcion.editText?.text.toString()
            val descripcionValidar = "[A-Za-z\\d\\-_\\sÑñ]{1,50}".toRegex()
            if (!descripcionValidar.matches(descripcion)) {
                binding.txtDescripcion.error = "Campo Solicitado"
                return@setOnClickListener
            }
            val marca = binding.txtMarca.editText?.text.toString()
            val Marcavalidar = "[A-Za-z\\d\\-_\\sÑñ]{1,50}".toRegex()
            if (!Marcavalidar.matches(marca)) {
                binding.txtMarca.error = "Campo Solicitado"
                return@setOnClickListener
            }
            var producto= Producto(productoItem.id,productoItem.idTipoProducto,codigoBarra, descripcion, marca,productoItem.imagenUrl,productoItem.estado)
            Log.i("act",producto.toString())
            productoViewModel.actualizar(producto)

        }
        binding.btnEliminar.setOnClickListener {
            binding.txtCodBarra.error = null
            binding.txtDescripcion.error = null
            binding.txtMarca.error = null

            val codigoBarra = binding.txtCodBarra.editText?.text.toString()
            val Codigovalidar = "[A-Za-z\\d\\-_\\sÑñ]{1,50}".toRegex()
            if (!Codigovalidar.matches(codigoBarra)) {
                binding.txtCodBarra.error = "Campo Solicitado"
                return@setOnClickListener
            }
            val descripcion = binding.txtDescripcion.editText?.text.toString()
            val descripcionValidar = "[A-Za-z\\d\\-_\\sÑñ]{1,50}".toRegex()
            if (!descripcionValidar.matches(descripcion)) {
                binding.txtDescripcion.error = "Campo Solicitado"
                return@setOnClickListener
            }
            val marca = binding.txtMarca.editText?.text.toString()
            val Marcavalidar = "[A-Za-z\\d\\-_\\sÑñ]{1,50}".toRegex()
            if (!Marcavalidar.matches(marca)) {
                binding.txtMarca.error = "Campo Solicitado"
                return@setOnClickListener
            }
            /* var producto = Producto(nombre, codigo, cantidad.toInt())
             productoViewModel.elimina(producto)*/

            findNavController().navigate(R.id.action_mntmProductoActualizacionFragment_to_mntmProductoListaEspFragment)
        }



        binding.btnRegresar.setOnClickListener {
            val direction: NavDirections =
                MntmProductoActualizacionFragmentDirections.actionMntmProductoActualizacionFragmentToMntmProductoListaEspFragment(
                    idtipoproducto = args.idtipoproducto,
                    nombretipoproducto = args.nombretipoproducto
                )
            findNavController().navigate(direction)
            //findNavController().navigate(R.id.action_mntmProductoActualizacionFragment_to_mntmProductoListaEspFragment)

        }
    }
}



