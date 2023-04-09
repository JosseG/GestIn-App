package com.nsgej.gestinapp.ui.menu.maintenance.empleado

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.databinding.FragmentMntmEmpleadoRegistroBinding
import com.nsgej.gestinapp.domain.model.Empleado
import com.nsgej.gestinapp.domain.model.Usuario
import com.nsgej.gestinapp.prefs
import com.nsgej.gestinapp.util.ProfileIconSetDialog
import com.nsgej.gestinapp.util.getSequenceIdEmployee
import com.nsgej.gestinapp.viewmodel.empleado.EmpleadoViewModel
import com.nsgej.gestinapp.viewmodel.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


@AndroidEntryPoint
class MntmEmpleadoRegistroFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private var idCargoObtained = ""
    private var idAlmacenObtained = ""
    private var codeEmpl = ""

    private var _binding: FragmentMntmEmpleadoRegistroBinding? = null
    val binding get() = _binding!!

    private val empleadoViewModel by viewModels<EmpleadoViewModel>()
    private val loginViewModel by viewModels<LoginViewModel>()
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
    ): View {
        Log.i("TAGs", prefs.stringPref.toString())
        _binding = FragmentMntmEmpleadoRegistroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        empleadoViewModel.obtenerUltimoEmpleado()

        empleadoViewModel.ultimoEmpleadoObtenido.observe(viewLifecycleOwner){
            codeEmpl = getSequenceIdEmployee(it.id)

            Log.i("UltimoCode",codeEmpl)
        }


        binding.btnRegresar.setOnClickListener {
            findNavController().navigate(R.id.action_mntmEmpleadoRegistroFragment2_to_mntmEmpleadoListaFragment)
        }

        empleadoViewModel.obtenerEmpleadoXId(prefs.stringPref.toString())

        empleadoViewModel.empleadoObtenidoLiveData.observe(viewLifecycleOwner){
            idAlmacenObtained = it.idAlmacen
        }

        binding.cargoimv.setOnClickListener {
            val add = ProfileIconSetDialog(
                onSubmitClickListener = {codCargo ->
                    idCargoObtained = codCargo
                    when (codCargo) {
                        "C00001" -> binding.cargoimv.setImageResource(R.drawable.ic_administrador)
                        "C00002"-> binding.cargoimv.setImageResource(R.drawable.ic_asistente_ventas)
                        "C00003"-> binding.cargoimv.setImageResource(R.drawable.ic_almacenero)
                        else -> {
                            print("x is neither 1 nor 2")
                        }
                    }

                }
            )
            add.show(requireActivity().supportFragmentManager, "dialog")
        }

        binding.btnRegistrarEmpleado.setOnClickListener{

            binding.txtNomempleado.error=null
            binding.txtApeempleado.error = null
            binding.txtTelefempleado.error= null
            binding.txtCorreoempleado.error= null
            binding.txtUsuarioempleado.error= null
            binding.txtContraempleado.error= null


            if(idCargoObtained.isEmpty()){
                MaterialAlertDialogBuilder(this.requireContext(), R.style.MaterialAlertDialog__Center)
                    .setTitle("-------------ERROR-------------")
                    .setMessage("Falta seleccionar el cargo")
                    .show()

                return@setOnClickListener
            }

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

            val usuario = binding.txtUsuarioempleado.editText?.text.toString()
            val usuarioValidar = "[A-Za-z\\d\\-_\\sÑñ]{1,70}".toRegex()
            if(usuario.isEmpty()){
                binding.txtUsuarioempleado.error ="Campo Requerido"
                return@setOnClickListener
            }
            if (!usuarioValidar.matches(usuario)) {
                binding.txtUsuarioempleado.error = "No cumple con los caracteres"
                return@setOnClickListener
            }

            val contrasena = binding.txtContraempleado.editText?.text.toString()
            val contrasenaValidar = "[A-Za-z\\d\\-_\\sÑñ]{1,70}".toRegex()
            if(contrasena.isEmpty()){
                binding.txtContraempleado.error ="Campo Requerido"
                return@setOnClickListener
            }
            if (!contrasenaValidar.matches(contrasena)) {
                binding.txtContraempleado.error = "No cumple con los caracteres"
                return@setOnClickListener
            }


            MaterialAlertDialogBuilder(this.requireContext(), R.style.MaterialAlertDialog__Center)
                .setTitle("-------------EXITO-------------")
                .setMessage("Nuevo empleado Registrado")
                .show()

            val empleado = Empleado(codeEmpl,idAlmacenObtained,nombre,apellido,correo,telefono,true)
            val usuarioO = Usuario(idEmpleado = codeEmpl, idCargo = idCargoObtained, alias = usuario, contrasena = contrasena, estado = true)

            loginViewModel.insertarEmpleadoPorAlmacen(empleado,idAlmacenObtained)
            loginViewModel.insertarUsuarioPorEmpleadoPorCargo(usuarioO,empleado,idCargoObtained)


            findNavController().navigate(R.id.action_mntmEmpleadoRegistroFragment2_to_mntmEmpleadoListaFragment)
        }


    }




    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MntmEmpleadoRegistroFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



}