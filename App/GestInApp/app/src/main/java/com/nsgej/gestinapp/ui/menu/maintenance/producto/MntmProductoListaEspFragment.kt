package com.nsgej.gestinapp.ui.menu.maintenance.producto

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.databinding.FragmentMntmProductoListaEspBinding
import com.nsgej.gestinapp.domain.model.Producto
import com.nsgej.gestinapp.domain.model.TipoProducto
import com.nsgej.gestinapp.domain.model.toDomain
import com.nsgej.gestinapp.ui.adapter.producto.ProductosEspecificoAdapter
import com.nsgej.gestinapp.ui.adapter.producto.ProductosGeneralAdapter
import com.nsgej.gestinapp.viewmodel.empleado.EmpleadoViewModel
import com.nsgej.gestinapp.viewmodel.inventario.ProductoAdapter
import com.nsgej.gestinapp.viewmodel.producto.ProductoViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class MntmProductoListaEspFragment : Fragment() {

    private var _binding: FragmentMntmProductoListaEspBinding? = null
    val binding get() = _binding!!


    //----------------------------------------------------------------
    private val args by navArgs<MntmProductoListaEspFragmentArgs>()
    private val productoViewModel by viewModels<ProductoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMntmProductoListaEspBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegresar.setOnClickListener {
            findNavController().navigate(R.id.action_mntmProductoListaEspFragment_to_mntmProductoListaGenFragment)
        }


        productoViewModel.obtenerProductosPorTipoProducto(args.idtipoproducto!!)
        productoViewModel.productosPorTipoObtenido.observe(viewLifecycleOwner) {
            val productoAdapter = ProductosEspecificoAdapter { producto, img->

                val extras = FragmentNavigatorExtras(
                    img to producto.id
                )

                val direction: NavDirections =
                    MntmProductoListaEspFragmentDirections.actionMntmProductoListaEspFragmentToMntmProductoActualizacionFragment(
                        imagen = img.transitionName,
                        idtipoproducto = args.idtipoproducto,
                        nombretipoproducto = args.nombretipoproducto,
                        idproducto = producto.id
                    )
                findNavController().navigate(direction,extras)
            }


            val productosEntity = it.productos
            for (e in productosEntity){
                Log.i("producto",e.descripcion)
            }
            binding.lblTipoProductoEnEsp.text = args.nombretipoproducto?.uppercase(Locale.ROOT)
            val productos: List<Producto> = productosEntity.map { el -> el.toDomain() }
            val manager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            binding.rvProductoListadoEsp.layoutManager = manager
            binding.rvProductoListadoEsp.adapter = productoAdapter
            productoViewModel.productos.observe(viewLifecycleOwner) {
                productoAdapter.clean()
                productoAdapter.cargarLista(productos)

            }
        }

        binding.faAgregar.setOnClickListener {
            findNavController().navigate(R.id.action_mntmProductoListaEspFragment_to_mntmProductoRegistroFragment)
        }
    }
}