package com.nsgej.gestinapp.ui.menu.maintenance.empleado

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.databinding.FragmentMntmEmpleadoListaBinding
import com.nsgej.gestinapp.prefs
import com.nsgej.gestinapp.ui.adapter.empleado.EmpleadoAdapter
import com.nsgej.gestinapp.viewmodel.empleado.EmpleadoViewModel
import dagger.hilt.android.AndroidEntryPoint


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
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



    private val empleadoViewModel by viewModels<EmpleadoViewModel>()

    private var canAdd:Boolean = false;

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
            if(canAdd){
                findNavController().navigate(R.id.action_mntmEmpleadoListaFragment_to_mntmEmpleadoRegistroFragment2)
            }else{
                Toast.makeText(this.context,"Disponible solo para administradores", Toast.LENGTH_SHORT).show()
            }

        }

        empleadoViewModel.obtenerFullDataEmpleadoXId(prefs.stringPref.toString())
        empleadoViewModel.allDataWObtenidoLiveData.observe(viewLifecycleOwner){worker ->
            if(worker.usuario.idCargo=="C00001"){
                canAdd = true
            }
        }

        binding.btnRegresar.setOnClickListener {
            findNavController().navigate(R.id.action_mntmEmpleadoListaFragment_to_mantenimientoFragment)
        }

        //PARA VER EL ACTUALIZAR DEBEN DE CODIFICARLO (haciendo clik en un item)

        val manager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

        binding.rvMantEmpleados.layoutManager=manager

        val empleadoAdapter = EmpleadoAdapter{ worker, imageView ->

            val extras = FragmentNavigatorExtras(
                imageView to worker.empleado.id
            )
            val direction: NavDirections =
                MntmEmpleadoListaFragmentDirections.actionMntmEmpleadoListaFragmentToMntmEmpleadoActualizarFragment(imagen = imageView.transitionName,usuario = canAdd)
            findNavController().navigate(direction,extras)
        }

        binding.rvMantEmpleados.adapter = empleadoAdapter

/*        empleadoViewModel.empleados.observe(viewLifecycleOwner){
            empleadoAdapter.clean()
            empleadoAdapter.cargarLista(it)
        }*/


        empleadoViewModel.obtenerEmpleadoXId(prefs.stringPref.toString())


        empleadoViewModel.empleadoObtenidoLiveData.observe(viewLifecycleOwner){
            empleadoViewModel.getAllDataWorkersForWarehouse(it.idAlmacen)
            empleadoViewModel.workersDataFiltradosXAlmacen.observe(viewLifecycleOwner){ listEmpl ->
                empleadoAdapter.clean()
                empleadoAdapter.cargarLista(listEmpl)
            }
        }


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