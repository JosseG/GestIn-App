package com.nsgej.gestinapp.ui.menu.maintenance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.databinding.FragmentMantenimientoBinding
import com.nsgej.gestinapp.domain.model.Producto
import com.nsgej.gestinapp.domain.model.TipoProducto
import com.nsgej.gestinapp.util.ProfileIconSetDialog
import com.nsgej.gestinapp.viewmodel.producto.ProductoViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
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

    val productooViewModel by viewModels<ProductoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentMantenimientoBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnProducto.setOnClickListener {
           /* callDialogFragment()*/
            findNavController().navigate(R.id.action_mantenimientoFragment_to_mntmProductoListaGenFragment)

        }
        binding.btnPersonal.setOnClickListener {
            findNavController().navigate(R.id.action_mantenimientoFragment_to_mntmEmpleadoListaFragment)
        }

        binding.btnRegresar.setOnClickListener {
            findNavController().navigate(R.id.action_mantenimientoFragment_to_menuFragment)
        }
        val tiposProducto = listOf(
            TipoProducto(nombre = "Procesadores", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imagenes%2F1.png?alt=media&token=e6e801ba-a935-477f-aaff-8648b45cb436"),
            TipoProducto(nombre = "Case", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imagenes%2F2.png?alt=media&token=38e17186-fbb8-4c05-96f4-82e9197ec952"),
            TipoProducto(nombre = "Memorias Ram", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imagenes%2F3.png?alt=media&token=2e234163-5975-42a4-ba1a-dc9f290db092"),
            TipoProducto(nombre = "Monitores", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imagenes%2F4.png?alt=media&token=c73d94cb-81b8-4f8e-bbaa-c1d0d858347f"),
            TipoProducto(nombre = "Mouse", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imagenes%2F5.png?alt=media&token=4c592fe8-7808-4185-918d-3bf8a0beae8f"),
            TipoProducto(nombre = "Fuentes de poder", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imagenes%2F6.png?alt=media&token=602779a7-1544-4e22-9163-2ae0503b7de3"),
            TipoProducto(nombre = "Teclados", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imagenes%2F7.png?alt=media&token=012ccc28-af81-428f-9770-4e361362ba6b"),
            TipoProducto(nombre = "Almacenamiento", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imagenes%2F8.png?alt=media&token=91a356c7-3506-4628-80f1-92673f8c575a"),
            TipoProducto(nombre = "Soportes Laptop", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imagenes%2F9.png?alt=media&token=65aa3fde-55c8-4e08-8d6d-ea9d4885e31d")
        )

        productooViewModel.insertarTiposProducto(tiposProducto)

        val productos = listOf(
            Producto("PR001",1,"1R001B001","i7 7600k","Intel",true),
            Producto("PR002",1,"1R001B331","i7 7700k","Intel",true),
            Producto("PR003",1,"1R001X001","i7 9600k","Intel",true),
            Producto("PR004",1,"1RXX3B001","i7 9700k","Intel",true),
            Producto("PR005",2,"1RRRRR001","Mattrex","Deep Cool",true),
            Producto("PR006",2,"1REE1B001","D-Shield","Deep Cool",true)
        )

        productooViewModel.insertarProductos(productos)

    }

    fun callDialogFragment() {
        val add = ProfileIconSetDialog()

        add.show(requireActivity().supportFragmentManager, "dialog")
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