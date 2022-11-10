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
import com.nsgej.gestinapp.domain.model.toDomain
import com.nsgej.gestinapp.ui.adapter.producto.ProductosEspecificoAdapter
import com.nsgej.gestinapp.viewmodel.producto.ProductoViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class MntmProductoListaEspFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentMntmProductoListaEspBinding? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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

            val manager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            binding.rvProductoListadoEsp.layoutManager = manager

            val productoAdapter = ProductosEspecificoAdapter { producto, imageView ->

                val extras = FragmentNavigatorExtras(
                    imageView to producto.id
                )
                val direction: NavDirections =
                    MntmProductoListaEspFragmentDirections.actionMntmProductoListaEspFragmentToMntmProductoActualizacionFragment(
                        imagen = imageView.transitionName, idtipoproducto = args.idtipoproducto, nombretipoproducto = args.nombretipoproducto
                    )

                findNavController().navigate(direction, extras)
            }

            val productosEntity = it.productos

            for (e in productosEntity){
                Log.i("producto",e.descripcion)
            }

            binding.lblTipoProductoEnEsp.text = args.nombretipoproducto?.uppercase(Locale.ROOT)

            val productos: List<Producto> = productosEntity.map { el -> el.toDomain() }

            productoAdapter.clean()
            productoAdapter.cargarLista(productos)
            binding.rvProductoListadoEsp.adapter = productoAdapter

        }


    }

/*    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MntmProductoListaEspFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/
}