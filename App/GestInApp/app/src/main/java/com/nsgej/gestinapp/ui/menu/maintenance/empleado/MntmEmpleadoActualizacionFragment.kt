package com.nsgej.gestinapp.ui.menu.maintenance.empleado

import android.os.Bundle
import android.text.Editable
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.databinding.FragmentMntmEmpleadoActualizacionBinding
import com.nsgej.gestinapp.domain.model.Empleado
import com.nsgej.gestinapp.domain.model.Usuario
import com.nsgej.gestinapp.prefs
import com.nsgej.gestinapp.ui.ActivityViewModel
import com.nsgej.gestinapp.util.ProfileIconSetDialog
import com.nsgej.gestinapp.util.WarningDeleteDialog
import com.nsgej.gestinapp.viewmodel.empleado.EmpleadoViewModel
import com.nsgej.gestinapp.viewmodel.login.LoginViewModel
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


    private val empleadoViewModel by viewModels<EmpleadoViewModel>()

    private val args by navArgs<MntmEmpleadoActualizacionFragmentArgs>()

    private lateinit var empleadoEnFocus: Empleado
    private lateinit var usuarioEnFocus: Usuario


    private val activityViewModel by viewModels<ActivityViewModel>()

    private val loginViewModel by viewModels<LoginViewModel>()

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

        empleadoViewModel.obtenerFullDataEmpleadoXId(args.imagen!!)
        binding.imageView2.transitionName = args.imagen


        if(args.usuario)
            isEditable(true)
        else
            isEditable(false)



        empleadoViewModel.allDataWObtenidoLiveData.observe(viewLifecycleOwner){ empleado ->
            empleadoEnFocus = empleado.empleado
            usuarioEnFocus = empleado.usuario
            when(empleado.usuario.idCargo) {
                "C00001" -> {
                    binding.imageView2.setImageResource(R.drawable.ic_administrador)
                }
                "C00002" -> {
                    binding.imageView2.setImageResource(R.drawable.ic_asistente_ventas)
                }
                "C00003" -> {
                    binding.imageView2.setImageResource(R.drawable.ic_almacenero)
                }
                else -> {
                    binding.imageView2.setImageResource(R.drawable.ic_administrador)
                }
            }


            binding.txtNomempleado.editText?.text =
                Editable.Factory.getInstance().newEditable(empleado.empleado.nombre)
            binding.txtApeempleado.editText?.text =
                Editable.Factory.getInstance().newEditable(empleado.empleado.apellido)
            binding.txtTelefempleado.editText?.text =
                Editable.Factory.getInstance().newEditable(empleado.empleado.telefono)
            binding.txtCorreoempleado.editText?.text =
                Editable.Factory.getInstance().newEditable(empleado.empleado.correo)

        }


        binding.btnActualizar.setOnClickListener {

            if(!args.usuario){
                Toast.makeText(this.context,"Disponible solo para administradores",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            binding.txtNomempleado.error=null
            binding.txtApeempleado.error = null
            binding.txtTelefempleado.error= null
            binding.txtCorreoempleado.error= null

            val nombre = binding.txtNomempleado.editText?.text.toString()
            val nombreValidar = "[A-Za-z\\d\\-_\\sÑñ]{1,70}".toRegex()
            if(nombre.isEmpty()){
                binding.txtNomempleado.error ="Campo Requerido"
                return@setOnClickListener
            }
            if (!nombreValidar.matches(nombre)) {
                binding.txtNomempleado.error = "No cumple con los caracteres"
                return@setOnClickListener
            }

            val apellido = binding.txtApeempleado.editText?.text.toString()
            val apellidoValidar = "[A-Za-z\\d\\-_\\sÑñ]{1,70}".toRegex()
            if(apellido.isEmpty()){
                binding.txtApeempleado.error ="Campo Requerido"
                return@setOnClickListener
            }
            if (!apellidoValidar.matches(apellido)) {
                binding.txtApeempleado.error = "No cumple con los caracteres"
                return@setOnClickListener
            }


            //-----------------------------------------------------
            val telefono = binding.txtTelefempleado.editText?.text.toString()
            val telefonoValidar = "[0-9]{9}".toRegex()
            if(telefono.isEmpty()){
                binding.txtTelefempleado.error ="Campo Requerido"
                return@setOnClickListener
            }
            if (!telefonoValidar.matches(telefono)) {
                binding.txtTelefempleado.error = "No cumple con los caracteres"
                return@setOnClickListener
            }

            val correo = binding.txtCorreoempleado.editText?.text.toString()
            val correoValidar = "[a-z\\d]+@[a-z]+\\.[a-z]{2,3}".toRegex()
            if(correo.isEmpty()){
                binding.txtCorreoempleado.error ="Campo Requerido"
                return@setOnClickListener
            }
            if (!correoValidar.matches(correo)) {
                binding.txtCorreoempleado.error = "No cumple con los caracteres"
                return@setOnClickListener
            }

            MaterialAlertDialogBuilder(this.requireContext(), R.style.MaterialAlertDialog__Center)
                .setTitle("-------------EXITO-------------")
                .setMessage("Los datos fueron actualizados con éxito")
                .show()

            val empleadoForUpdate = Empleado(empleadoEnFocus.id,empleadoEnFocus.idAlmacen,nombre,apellido,correo,telefono,true)

            empleadoViewModel.actualizar(empleadoForUpdate)

            findNavController().navigate(R.id.action_mntmEmpleadoActualizarFragment_to_mntmEmpleadoListaFragment)
        }

        binding.btnEliminar.setOnClickListener {
            if(!args.usuario){
                Toast.makeText(this.context,"Disponible solo para administradores",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(prefs.stringPref.toString() == empleadoEnFocus.id){


                val add = WarningDeleteDialog(
                    onSubmitClickListener = {acepta ->
                        if(acepta){
                            activityViewModel.clearData()

                            activityViewModel.getStatus()
                        }

                    }
                )
                add.show(requireActivity().supportFragmentManager, "dialog")


                return@setOnClickListener
            }


            loginViewModel.eliminarUsuario(usuarioEnFocus)
            empleadoViewModel.eliminar(empleadoEnFocus)

        }

        activityViewModel.onSession.observe(viewLifecycleOwner){
            if (!it) {
                loginViewModel.eliminarUsuario(usuarioEnFocus)
                empleadoViewModel.eliminar(empleadoEnFocus)
                findNavController().navigate(R.id.action_mntmEmpleadoActualizarFragment_to_loginFragment)
            }
        }

        binding.btnRegresar.setOnClickListener {
            findNavController().navigate(R.id.action_mntmEmpleadoActualizarFragment_to_mntmEmpleadoListaFragment)
        }
    }

    private fun isEditable(editable: Boolean) {
        binding.txtNomempleado.isEnabled = editable
        binding.txtApeempleado.isEnabled = editable
        binding.txtTelefempleado.isEnabled = editable
        binding.txtCorreoempleado.isEnabled = editable
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