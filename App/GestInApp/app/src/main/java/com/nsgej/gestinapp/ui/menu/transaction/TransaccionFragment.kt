package com.nsgej.gestinapp.ui.menu.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.databinding.FragmentTransaccionBinding
import com.nsgej.gestinapp.domain.model.Producto
import com.nsgej.gestinapp.domain.model.TipoInventario
import com.nsgej.gestinapp.viewmodel.inventario.TipoInventarioViewModel
import com.nsgej.gestinapp.viewmodel.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class TransaccionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val loginViewModel by viewModels<LoginViewModel>()

    private val viewmodeltipoInventario  by viewModels<TipoInventarioViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        insertarIpoInventario()
        insertarProducto()
    }
    private var _binding: FragmentTransaccionBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTransaccionBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnInventario.setOnClickListener {
            findNavController().navigate(R.id.action_transaccionFragment_to_trscInventarioFragment)
        }

        binding.btnRegresar.setOnClickListener {
            findNavController().navigate(R.id.action_transaccionFragment_to_menuFragment)
        }



    }

    fun insertarIpoInventario(){

        val tipo1 = TipoInventario(nombre = "Ingresado")
        val tipo2 = TipoInventario(nombre = "Salida")
        viewmodeltipoInventario.RegistraTipoInventario(tipo1)
        viewmodeltipoInventario.RegistraTipoInventario(tipo2)

    }
    fun insertarProducto(){

        val prod1=  Producto(id="PR007", idTipoProducto = 4, codigoBarra = "01010101", descripcion = "Monitor", marca = "Lenovo", estado = true)
        loginViewModel.RegistraProducto(prod1)

    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TransaccionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}