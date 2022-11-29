package com.nsgej.gestinapp.ui.menu.transaction.inventario

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.data.dataclass.InventarioDC
import com.nsgej.gestinapp.databinding.FragmentTrscInventarioBinding
import com.nsgej.gestinapp.domain.model.*
import com.nsgej.gestinapp.viewmodel.inventario.*
import com.nsgej.gestinapp.viewmodel.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrscInventarioFragment : Fragment() {

    var codigoTp=0
    var  nombreTP=""
    var codp=""
    var desc=""
    var cod_al =""
    var desc_al = ""
    var cod_pers = ""
    var desc_pers = ""
    var cod=0

    private var _binding: FragmentTrscInventarioBinding? = null
    val binding get() = _binding!!

    private val viewmodelInventario by viewModels<InventarioViewModel>()

    private val viewmodeltipoInventario by viewModels<TipoInventarioViewModel>()


    private val loginViewModel by viewModels<LoginViewModel>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTrscInventarioBinding.inflate(inflater, container, false)

        return binding.root

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegresar.setOnClickListener {
            findNavController().navigate(R.id.action_trscInventarioFragment_to_transaccionFragment)
        }


        val movimientoAuto: AutoCompleteTextView = binding.autoCompletemovimiento
        val productoAuto: AutoCompleteTextView = binding.autoCompleteProduct
        val almacenAuto: AutoCompleteTextView = binding.autoCompleteAlmc
        val personalAuto: AutoCompleteTextView = binding.autoCompletePers


        viewmodeltipoInventario.listarTipoInventario.observe(viewLifecycleOwner) { lista ->
            TipoInventarioAdapter(requireContext(), lista).also { tp ->
                movimientoAuto.setAdapter(tp)
            }
        }
        movimientoAuto.setOnItemClickListener { parent, _, position, _ ->
            val tipoInv = parent.adapter.getItem(position) as TipoInventario
            codigoTp = tipoInv.id
            nombreTP = tipoInv.nombre
        }



        loginViewModel.listarProductos.observe(viewLifecycleOwner) { lista ->
            ProductoAdapter(requireContext(), lista).also { lp ->
                productoAuto.setAdapter(lp)
            }
        }
        productoAuto.setOnItemClickListener { parent, _, position, _ ->
            val tipoInv = parent.adapter.getItem(position) as Producto
            codp = tipoInv.id
            desc = tipoInv.descripcion
        }


        loginViewModel.listaAlmacenes.observe(viewLifecycleOwner) { lista ->
            AlmacenAdapter(requireContext(),lista).also { al ->
                almacenAuto.setAdapter(al)
            }
        }
        almacenAuto.setOnItemClickListener { parent, _, position, _ ->
            val tipoInv = parent.adapter.getItem(position) as Almacen
            cod_al = tipoInv.id
            desc_al = tipoInv.descripcion
        }


        loginViewModel.listaempleados.observe(viewLifecycleOwner) { lista ->
            PersonalAdapter(requireContext(),lista).also { pers ->
                personalAuto.setAdapter(pers)
            }
        }
        personalAuto.setOnItemClickListener { parent, _, position, _ ->
            val tipoInv = parent.adapter.getItem(position) as Empleado
            cod_pers = tipoInv.id
            desc_pers = tipoInv.nombre
        }

        viewmodelInventario.nuevoregistroAgregado()


        viewmodelInventario.codigoObtenido.observe(viewLifecycleOwner){
            cod = it
            Log.i(TAG, cod.toString())

            binding.btnRegistrarinventario.setOnClickListener {




                val tipo = binding.txtMovimiento.editText?.text.toString()
                if (tipo.isEmpty()) {
                    binding.txtMovimiento.error = "seleccione un producto"
                    return@setOnClickListener
                }

                val prod = binding.txtProducto.editText?.text.toString()
                if (prod.isEmpty()) {
                    binding.txtProducto.error = "seleccione un producto"
                    return@setOnClickListener
                }
                val alm = binding.txtAlmacen.editText?.text.toString()
                if (alm.isEmpty()) {
                    binding.txtAlmacen.error = "Seleccione un almacén"
                    return@setOnClickListener
                }
                val pers = binding.txtPersonal.editText?.text.toString()
                if (pers.isEmpty()) {
                    binding.txtPersonal.error = "Seleccione un responsable"
                    return@setOnClickListener
                }
                val cant = binding.txtCantidad.editText?.text.toString()
                if (cant.isEmpty()) {
                    binding.txtCantidad.error = "campo requerido"
                    return@setOnClickListener
                } else if (!cant.isDigitsOnly()) {
                    binding.txtCantidad.error = "Este campo sólo acepta números"
                    return@setOnClickListener
                }




                if(codigoTp == 1){
                    val inventario = Inventario(idTipoInventario = codigoTp, idProducto = codp, idAlmacen = cod_al, idEmpleado = cod_pers, cantidad = cant.toInt())
                    viewmodelInventario.RegistraInventario(inventario)
                }
                if(codigoTp == 2){
                    val inventarioDC = InventarioDC(codigo = cod,idTipoInventario = codigoTp, idProducto = codp, idAlmacen = cod_al, idEmpleado = cod_pers, cantidad = cant.toInt())
                    viewmodelInventario.RegistraInventarioFireabase(inventarioDC)
                    viewmodelInventario.nuevoregistroAgregado()
                }




            }
        }




    }

}
