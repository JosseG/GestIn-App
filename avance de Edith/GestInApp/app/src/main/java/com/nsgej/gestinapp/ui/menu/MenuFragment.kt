package com.nsgej.gestinapp.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.databinding.FragmentMenuBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MenuFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private var _binding : FragmentMenuBinding? = null
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
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
            findNavController().navigate(R.id.action_menuFragment_to_perfilFragment)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}