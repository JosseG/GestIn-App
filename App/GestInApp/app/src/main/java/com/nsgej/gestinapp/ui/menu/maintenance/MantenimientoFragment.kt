package com.nsgej.gestinapp.ui.menu.maintenance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.databinding.FragmentMantenimientoBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MantenimientoFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentMantenimientoBinding? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentMantenimientoBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnProducto.setOnClickListener {
            findNavController().navigate(R.id.action_mantenimientoFragment_to_mantenimientoAppFragment)
        }
        binding.btnPersonal.setOnClickListener {
            findNavController().navigate(R.id.action_mantenimientoFragment_to_mntmEmpleadoListaFragment)
        }

        binding.btnRegresar.setOnClickListener {
            findNavController().navigate(R.id.action_mantenimientoFragment_to_menuFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MantenimientoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}