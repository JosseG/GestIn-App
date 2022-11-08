package com.nsgej.gestinapp.ui.menu.maintenance.empleado

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.databinding.FragmentMntmEmpleadoActualizacionBinding
import dagger.hilt.android.AndroidEntryPoint


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class MntmEmpleadoActualizacionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentMntmEmpleadoActualizacionBinding? = null
    val binding get() = _binding!!



    private val args by navArgs<MntmEmpleadoActualizacionFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMntmEmpleadoActualizacionBinding.inflate(inflater, container, false)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("id",args.imagen.toString())

        binding.imageView2.transitionName = args.imagen
        binding.imageView2.setImageResource(R.drawable.ic_administrador)

        binding.btnRegresar.setOnClickListener {
            findNavController().navigate(R.id.action_mntmEmpleadoActualizarFragment_to_mntmEmpleadoListaFragment)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MntmEmpleadoActualizacionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}