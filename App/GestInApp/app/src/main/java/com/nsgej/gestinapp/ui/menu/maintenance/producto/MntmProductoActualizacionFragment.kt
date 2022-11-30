package com.nsgej.gestinapp.ui.menu.maintenance.producto

import android.content.Context
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.databinding.FragmentMntmProductoActualizacionBinding
import com.nsgej.gestinapp.domain.model.Producto
import com.nsgej.gestinapp.domain.model.ProductoAlmacen
import com.nsgej.gestinapp.prefs
import com.nsgej.gestinapp.ui.adapter.producto.ProductosEspecificoAdapter
import com.nsgej.gestinapp.ui.adapter.producto.ProductosEspecificoViewHolder
import com.nsgej.gestinapp.viewmodel.empleado.EmpleadoViewModel
import com.nsgej.gestinapp.viewmodel.producto.ProductoViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class MntmProductoActualizacionFragment : Fragment() {

    private var _binding: FragmentMntmProductoActualizacionBinding? = null
    val binding get() = _binding!!

    private val args by navArgs<MntmProductoActualizacionFragmentArgs>()
    private val productoViewModel by viewModels<ProductoViewModel>()
    private val empleadoViewModel by viewModels<EmpleadoViewModel>()
    lateinit var productoItem: Producto

    var cantidadtemporal  = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMntmProductoActualizacionBinding.inflate(inflater, container, false)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        return binding.root
    }

    lateinit var contexto: Context
    var idalmacen = ""
    override fun onAttach(context: Context) {
        super.onAttach(context)
        contexto = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        productoViewModel.obtenerProducto(args.idproducto!!)


        productoViewModel.productoPorTipoObtenido.observe(viewLifecycleOwner) { producto ->
            empleadoViewModel.obtenerEmpleadoXId(prefs.stringPref.toString())
            empleadoViewModel.empleadoObtenidoLiveData.observe(viewLifecycleOwner){
                productoViewModel.obtenerProductoAlmacen(args.idproducto!!,it.idAlmacen)
            }

            productoViewModel.productoAlmacen.observe(viewLifecycleOwner){productoalm->

                productoItem = producto

                binding.txtCodBarra.editText?.text =
                    Editable.Factory.getInstance().newEditable(producto.codigoBarra)
                binding.txtDescripcion.editText?.text =
                    Editable.Factory.getInstance().newEditable(producto.descripcion)
                cantidadtemporal = productoalm.cantidad.toString()
                binding.txtCantidadProd.editText?.text =
                    Editable.Factory.getInstance().newEditable(productoalm.cantidad.toString())
                binding.imgvProducto.transitionName = args.imagen
                Glide.with(this).load(producto.imagenUrl).into(binding.imgvProducto)

            }


        }

        empleadoViewModel.obtenerEmpleadoXId(prefs.stringPref.toString())

        empleadoViewModel.empleadoObtenidoLiveData.observe(viewLifecycleOwner) { empleado ->
            idalmacen = empleado.idAlmacen
        }


        //--------------------------------------------------------------
        binding.btnActualizar.setOnClickListener {
            binding.txtCodBarra.error = null
            binding.txtDescripcion.error = null
            binding.txtCantidadProd.error = null

            val codigoBarra = binding.txtCodBarra.editText?.text.toString()
            val Codigovalidar = "[A-Za-z\\d\\-_\\sÑñ]{8,20}".toRegex()
            if (codigoBarra.isEmpty()) {
                binding.txtCodBarra.error = "Campo Requerido"
                return@setOnClickListener
            }
            if (!Codigovalidar.matches(codigoBarra)) {
                binding.txtCodBarra.error = "De 8 a 20 caracteres"
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
            val cantidad = binding.txtCantidadProd.editText?.text.toString()
            val Marcavalidar = "[0-9]{1,2}".toRegex()
            if (!Marcavalidar.matches(cantidad)) {
                binding.txtCantidadProd.error = "Campo Requerido"
                return@setOnClickListener
            }
            MaterialAlertDialogBuilder(contexto)
                .setTitle("-------------EXITO-------------")
                .setMessage("Producto Actualizado")
                .show()

/*
            val productoAlmacen = ProductoAlmacen(args.idproducto!!, idalmacen, cantidad.toInt(), true)
            productoViewModel.actualizarProductoAlmacen(productoAlmacen)
*/

            productoViewModel.obtenerProductoAlmacen(args.idproducto!!,idalmacen)
            productoViewModel.productoAlmacen.observe(viewLifecycleOwner){ prodAlma->
                prodAlma.cantidad = cantidad.toInt();
                productoViewModel.actualizarProductoAlmacen(prodAlma)
            }

            val direction: NavDirections =
                MntmProductoActualizacionFragmentDirections.actionMntmProductoActualizacionFragmentToMntmProductoListaEspFragment(
                    idtipoproducto = args.idtipoproducto,
                    nombretipoproducto = args.nombretipoproducto
                )
            findNavController().navigate(direction)

        }
        binding.btnEliminar.setOnClickListener {
            binding.txtCodBarra.error = null
            binding.txtDescripcion.error = null
            binding.txtCantidadProd.error = null

            val codigoBarra = binding.txtCodBarra.editText?.text.toString()
            val Codigovalidar = "[A-Za-z\\d\\-_\\sÑñ]{8,20}".toRegex()
            if (codigoBarra.isEmpty()) {
                binding.txtCodBarra.error = "Campo Requerido"
                return@setOnClickListener
            }
            if (!Codigovalidar.matches(codigoBarra)) {
                binding.txtCodBarra.error = "De 8 a 20 caracteres"
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
            val cantidadprod = binding.txtCantidadProd.editText?.text.toString()
            val Marcavalidar = "[A-Za-z\\d\\-_\\sÑñ]{1,20}".toRegex()
            if (!Marcavalidar.matches(cantidadprod)) {
                binding.txtCantidadProd.error = "Campo Requerido"
                return@setOnClickListener
            }

            MaterialAlertDialogBuilder(contexto)
                .setTitle("-------------EXITO-------------")
                .setMessage("Producto Eliminado")
                .show()


            val productoAlmacen = ProductoAlmacen(args.idproducto!!, idalmacen, 0, true)
            productoViewModel.eliminarProductoAlmacen(productoAlmacen)

            val direction: NavDirections =
                MntmProductoActualizacionFragmentDirections.actionMntmProductoActualizacionFragmentToMntmProductoListaEspFragment(
                    idtipoproducto = args.idtipoproducto,
                    nombretipoproducto = args.nombretipoproducto
                )
            findNavController().navigate(direction)
        }

        binding.btnRegresar.setOnClickListener {
            val direction: NavDirections =
                MntmProductoActualizacionFragmentDirections.actionMntmProductoActualizacionFragmentToMntmProductoListaEspFragment(
                    idtipoproducto = args.idtipoproducto,
                    nombretipoproducto = args.nombretipoproducto
                )
            findNavController().navigate(direction)

        }
    }
}



