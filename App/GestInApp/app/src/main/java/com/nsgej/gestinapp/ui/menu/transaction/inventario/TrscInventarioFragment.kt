package com.nsgej.gestinapp.ui.menu.transaction.inventario

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.Editable
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
import com.nsgej.gestinapp.prefs
import com.nsgej.gestinapp.viewmodel.empleado.EmpleadoViewModel
import com.nsgej.gestinapp.viewmodel.inventario.*
import com.nsgej.gestinapp.viewmodel.login.LoginViewModel
import com.nsgej.gestinapp.viewmodel.producto.ProductoViewModel
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

    private val productoViewModel by viewModels<ProductoViewModel>()
    private val loginViewModel by viewModels<LoginViewModel>()
    private val empleadoViewModel by viewModels<EmpleadoViewModel>()

    var idalmacen = ""
    var nombrepersonal = ""

    var codigosinincrementar =0
    var idalmaceningreso = ""
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

        binding.txtAlmacen.isEnabled = true
        binding.txtCantidad.isEnabled = true
        binding.txtMovimiento.isEnabled = true
        binding.txtProducto.isEnabled = true

        empleadoViewModel.obtenerEmpleadoXId(prefs.stringPref.toString())

        empleadoViewModel.empleadoObtenidoLiveData.observe(viewLifecycleOwner) { empleado ->
            Log.i("TAG333", empleado.idAlmacen)
            idalmacen = empleado.idAlmacen
            nombrepersonal = empleado.nombre
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
        movimientoAuto.setOnItemClickListener { parentmov, _, positionmov, _ ->
            val tipoInvMov = parentmov.adapter.getItem(positionmov) as TipoInventario
            codigoTp = tipoInvMov.id
            nombreTP = tipoInvMov.nombre

            if(tipoInvMov.nombre == "Ingresado"){
                binding.txtCantidad.isEnabled = false

                Log.i("TAG3","Esta en ingreso")

                viewmodelInventario.listarTodoInventarioFirebase(idalmacen)
                viewmodelInventario.listaTodoInventarioFirebase.observe(viewLifecycleOwner){ inventarioItemFs ->

                    if(inventarioItemFs.isEmpty()){
                        Log.i("No hay TAG", "vacio")
                        return@observe
                    }else{
                        viewmodelInventario.listarTodoInventario.observe(viewLifecycleOwner) { inventarioItemRoom ->

                            var listainventarionuevo = mutableListOf<InventarioDC>()
                            var listainventariosec = mutableListOf<InventarioDC>()

                            if(inventarioItemRoom.isEmpty()){
                                listainventarionuevo.clear()
                                inventarioItemFs.forEach el@{ invfs ->

                                    if(invfs != null){
                                        listainventarionuevo.add(invfs)
                                    }

                                }
                            }else{
                                listainventarionuevo.clear()
                                inventarioItemFs.forEach el@{ invfs ->

                                    if(invfs != null){
                                        inventarioItemRoom.forEach { inv ->
                                            if(invfs.codigo == inv.id){
                                                return@el
                                            }else{
                                                listainventarionuevo.add(invfs)
                                            }
                                        }
                                    }

                                }

                                inventarioItemFs.forEach { inventarioDeFirebase ->
                                    if (inventarioDeFirebase!==null){
                                        Log.i("TAG INVEN",inventarioDeFirebase.idProducto + " " + inventarioDeFirebase.codigo+ " " + inventarioDeFirebase.idTipoInventario)
                                    }
                                }
                            }
                            ProductoInventarioAdapter(requireContext(), listainventarionuevo.toList()).also { lp ->
                                productoAuto.setAdapter(lp)
                            }

                            productoAuto.setOnItemClickListener { parent, _, position, _ ->
                                val tipoInvr = parent.adapter.getItem(position) as InventarioDC
                                codp = tipoInvr.idProducto

                                codigosinincrementar = tipoInvr.codigo
                                idalmaceningreso = tipoInvr.idAlmacen

                                binding.txtAlmacen.editText?.text  = Editable.Factory.getInstance().newEditable(tipoInvr.idAlmacen)
                                binding.txtAlmacen.isEnabled = false


                                binding.txtPersonal.editText?.text  = Editable.Factory.getInstance().newEditable(tipoInvr.idEmpleado)
                                binding.txtPersonal.isEnabled = false


                                binding.txtCantidad.editText?.text  = Editable.Factory.getInstance().newEditable(tipoInvr.cantidad.toString())
                                binding.txtCantidad.isEnabled = false

                            }
                        }
                    }


                }



            }else{
                binding.txtCantidad.isEnabled = true


                productoViewModel.obtenerProductosPorAlmacen(idalmacen)

                productoViewModel.listarProductosXMiAlmacen.observe(viewLifecycleOwner){ listaprod ->

                    ProductoAdapter(requireContext(), listaprod).also { lp ->
                        productoAuto.setAdapter(lp)
                    }

                }

                productoAuto.setOnItemClickListener { parentprodm, _, positionprodm, _ ->
                    val tipoprodsalida = parentprodm.adapter.getItem(positionprodm) as Producto
                    codp = tipoprodsalida.id



                }

                viewmodelInventario.listarSinMiAlmacen(idalmacen)

                viewmodelInventario.listaSinMiAlmacenObtenido.observe(viewLifecycleOwner){ listaSMalmacen ->
                    AlmacenAdapter(requireContext(),listaSMalmacen).also { al ->
                        almacenAuto.setAdapter(al)
                    }
                }
                almacenAuto.setOnItemClickListener { parentalma, _, positionalma, _ ->
                    val almacenobt = parentalma.adapter.getItem(positionalma) as Almacen
                    cod_al = almacenobt.id
                    desc_al = almacenobt.descripcion


                    binding.txtPersonal.editText?.text = Editable.Factory.getInstance().newEditable(nombrepersonal)
                    binding.txtPersonal.isEnabled = false
                }

/*                loginViewModel.listarProductos.observe(viewLifecycleOwner) { lista ->
                    ProductoAdapter(requireContext(), lista).also { lp ->
                        productoAuto.setAdapter(lp)
                    }
                }*/



            }
        }







/*        loginViewModel.listaAlmacenes.observe(viewLifecycleOwner) { lista ->
            AlmacenAdapter(requireContext(),lista).also { al ->
                almacenAuto.setAdapter(al)
            }
        }
        almacenAuto.setOnItemClickListener { parent, _, position, _ ->
            val tipoInv = parent.adapter.getItem(position) as Almacen
            cod_al = tipoInv.id
            desc_al = tipoInv.descripcion
        }*/


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


        viewmodelInventario.codigoObtenido.observe(viewLifecycleOwner){ codigodeFirebase ->
            cod = codigodeFirebase

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
                    val inventario = Inventario(codigosinincrementar,idTipoInventario = codigoTp, idProducto = codp, idAlmacen = idalmaceningreso, idEmpleado = cod_pers, cantidad = cant.toInt())

                    viewmodelInventario.RegistraInventario(inventario)
                    Log.i("SUPERTAG", codigosinincrementar.toString() + " " + codigoTp + "  " + codp  + " " + idalmaceningreso + " " + cod_pers + " " + cant)

                    productoViewModel.obtenerProductoAlmacen(codp,idalmacen)
                    productoViewModel.productoAlmacen.observe(viewLifecycleOwner){ prodAlma->
                        prodAlma.cantidad += cant.toInt();
                        productoViewModel.actualizarProductoAlmacen(prodAlma)
                    }

                }
                if(codigoTp == 2){
                    val inventarioDC = InventarioDC(codigo = cod,idTipoInventario = codigoTp, idProducto = codp, idAlmacen = cod_al, idEmpleado = cod_pers, cantidad = cant.toInt())
                    viewmodelInventario.RegistraInventarioFireabase(inventarioDC)
                    viewmodelInventario.nuevoregistroAgregado()
                    /*val productoAlmacen = ProductoAlmacen(codp, idalmacen, cant.toInt(), true)*/
                    productoViewModel.obtenerProductoAlmacen(codp,idalmacen)
                    productoViewModel.productoAlmacen.observe(viewLifecycleOwner){ prodAlma->
                        prodAlma.cantidad -= cant.toInt();
                        productoViewModel.actualizarProductoAlmacen(prodAlma)
                    }
                }




            }
        }




    }

}
