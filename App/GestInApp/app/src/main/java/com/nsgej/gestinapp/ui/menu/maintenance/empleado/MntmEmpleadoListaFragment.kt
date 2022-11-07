package com.nsgej.gestinapp.ui.menu.maintenance.empleado

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.databinding.FragmentMntmEmpleadoListaBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MntmEmpleadoListaFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private var _binding: FragmentMntmEmpleadoListaBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMntmEmpleadoListaBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.faRegistrar.setOnClickListener {
            findNavController().navigate(R.id.action_mntmEmpleadoListaFragment_to_mntmEmpleadoRegistroFragment2 )
        }

        binding.btnRegresar.setOnClickListener {
            findNavController().navigate(R.id.action_mntmEmpleadoListaFragment_to_mantenimientoFragment)
        }

        //PARA VER EL ACTUALIZAR DEBEN DE CODIFICARLO (haciendo clik en un item)
    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MntmEmpleadoListaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}