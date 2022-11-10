package com.nsgej.gestinapp.ui.menu.maintenance.producto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.databinding.FragmentMntmProductoListaGenBinding
import com.nsgej.gestinapp.ui.adapter.producto.ProductosGeneralAdapter
import com.nsgej.gestinapp.viewmodel.producto.ProductoViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class MntmProductoListaGenFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentMntmProductoListaGenBinding? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    val productoViewModel by viewModels<ProductoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMntmProductoListaGenBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegresar.setOnClickListener {
            findNavController().navigate(R.id.action_mntmProductoListaGenFragment_to_mantenimientoFragment)
        }

        val tipoProductoAdapter = ProductosGeneralAdapter{ tipoProducto->

            val direction: NavDirections =
                MntmProductoListaGenFragmentDirections.actionMntmProductoListaGenFragmentToMntmProductoListaEspFragment(
                    tipoProducto.id.toString(),
                    tipoProducto.nombre
                )

            findNavController().navigate(direction)
        }

        val manager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)


        binding.rvListaTipoProductos.layoutManager=manager
        binding.rvListaTipoProductos.adapter = tipoProductoAdapter

        productoViewModel.tiposProducto.observe(viewLifecycleOwner){
            tipoProductoAdapter.clean()
            tipoProductoAdapter.cargarLista(it)
        }


    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MntmProductoListaGenFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}