package com.nsgej.gestinapp.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.databinding.FragmentMenuBinding
import com.nsgej.gestinapp.prefs
import com.nsgej.gestinapp.viewmodel.MenuViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class MenuFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!


    private val menuViewModel by viewModels<MenuViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = prefs.stringPref

        if (id != null) {
            menuViewModel.obtenerEmpleado(id)
        }

        menuViewModel.empleado.observe(viewLifecycleOwner) { empleado ->
            binding.lblNombreuser.text = empleado.nombre


            binding.btnMantenimiento.setOnClickListener {
                findNavController().navigate(R.id.action_menuFragment_to_mantenimientoFragment)
            }
            binding.btnTransaccion.setOnClickListener {
                findNavController().navigate(R.id.action_menuFragment_to_transaccionFragment)
            }
            binding.btnReporte.setOnClickListener {
                findNavController().navigate(R.id.action_menuFragment_to_reportesFragment)
            }
            binding.btnPerfil.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("empleado", empleado)
                findNavController().navigate(R.id.action_menuFragment_to_perfilFragment,bundle)
            }
        }



    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = MenuFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }
}