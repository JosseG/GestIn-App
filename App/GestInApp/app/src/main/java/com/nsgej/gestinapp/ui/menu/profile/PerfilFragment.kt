package com.nsgej.gestinapp.ui.menu.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.databinding.FragmentPerfilBinding
import com.nsgej.gestinapp.domain.model.Empleado
import com.nsgej.gestinapp.prefs
import com.nsgej.gestinapp.ui.ActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class PerfilFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentPerfilBinding? = null
    val binding get() = _binding!!


    private val activityViewModel by viewModels<ActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var empleado: Empleado

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPerfilBinding.inflate(inflater, container, false)

        empleado = arguments?.getSerializable("empleado") as Empleado
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.txtNombrep.text = empleado.nombre
        binding.txtApellidop.text = empleado.apellido
        binding.txtCorreop.text = empleado.correo
        binding.txtTelefonop.text = empleado.telefono

        binding.btnRegresar.setOnClickListener {
            findNavController().navigate(R.id.action_perfilFragment_to_menuFragment)
        }

        binding.btnCerrarSesion.setOnClickListener {
            activityViewModel.clearData()
            activityViewModel.getStatus()
        }

        activityViewModel.onSession.observe(viewLifecycleOwner) {
            if (!it) {
                findNavController().navigate(R.id.action_perfilFragment_to_loginFragment)
            }
        }
    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PerfilFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}