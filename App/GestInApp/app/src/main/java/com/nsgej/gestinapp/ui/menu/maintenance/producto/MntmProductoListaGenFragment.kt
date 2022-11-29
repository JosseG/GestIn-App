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


@AndroidEntryPoint
class MntmProductoListaGenFragment : Fragment() {

    private var _binding: FragmentMntmProductoListaGenBinding? = null
    val binding get() = _binding!!


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
                    tipoProducto.id,
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

}