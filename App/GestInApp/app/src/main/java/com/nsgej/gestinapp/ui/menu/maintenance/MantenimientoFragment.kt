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
            Producto("PR001",1,"1R001B001","i9 7600k","Intel", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgProcesadores%2Fcorei9.png?alt=media&token=87aa66d3-0f83-4c40-86e5-109f343cdefe",true),
            Producto("PR002",1,"1R001B331","i7 7700k","Intel", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgProcesadores%2Fcorei7.png?alt=media&token=8bab6b5f-ae77-4e5f-b479-d4f39308f8c4",true),
            Producto("PR003",1,"1R001X001","i5 9600k","Intel", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgProcesadores%2Fcorei5.png?alt=media&token=3fa9155c-7b6a-4d61-b7e4-cbab90f405b8",true),
            Producto("PR004",1,"1RXX3B001","i3 9700k","Intel", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgProcesadores%2Fcorei3.png?alt=media&token=9c4bffeb-129f-4ba5-a493-e73eaca33a1f",true),

            Producto("PR005",2,"1RRRRR001","Mattrex","Deep Cool", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgCase%2F1.png?alt=media&token=143e88a8-c548-4155-8f6f-dc5234df8ade",true),
            Producto("PR006",2,"1REE1B001","D-Shield","Deep Cool", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgCase%2F2.png?alt=media&token=942f98b9-eb32-48c6-a5cb-64cad87d032a",true),
            Producto("PR007",2,"CASEMX410","MX410-G","COUGAR", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgCase%2F3.png?alt=media&token=f4de8872-0528-434f-99f0-09c5db8c52a7",true),
            Producto("PR008",2,"CASEMB520","MB520 BLACK","COOLER MASTER", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgCase%2F4.png?alt=media&token=4a5e99ca-e1af-47fc-bf4b-de376fc153c1",true),
            Producto("PR009",2,"CASETD500","TD500 MESH WHITE","COOLER MASTER", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgCase%2F5.png?alt=media&token=f2030340-afea-4988-923a-7512f5f9ba99",true),

            Producto("PR011",3,"KF426C16B","FURY BEAST","KINGSTON", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgMemorias%2F1.png?alt=media&token=39cb0f6b-9223-4e56-8781-665363c71a9e",true),
            Producto("PR012",3,"BL9BWWA23","HT100","ACER", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgMemorias%2F2.png?alt=media&token=1aab215a-c8e7-4a67-b8ea-144e3129890f",true),
            Producto("PR013",3,"KF432C16R","FURY RENEGADE","KINGSTON", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgMemorias%2F3.png?alt=media&token=f16f3077-31d8-4088-a153-33e02c203d0e",true),
            Producto("PR014",3,"2E2M7AA#A","S1 SERIES","HP", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgMemorias%2F4.png?alt=media&token=ea530596-0ae6-46f2-b5aa-e1887da89b98",true),
            Producto("PR015",3,"CT8G4SFRA","BLUE","CRUCIAL", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgMemorias%2F5.png?alt=media&token=3880c1b7-58cc-495f-a061-02f4cd2bd0ec",true),
            Producto("PR016",3,"KF432S20I","FURY IMPACT","KINGSTON", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgMemorias%2F6.png?alt=media&token=5fce843e-d365-423b-a68a-037b0d909dca",true),

            Producto("PR017",4,"ODYSSEYC2","CURVO-VA-144HZ","SAMSUNG", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgMonitores%2F1.png?alt=media&token=351b475a-ae36-450c-9d63-a99efd1d0fd0",true),
            Producto("PR018",4,"LC27G55TQ","CURVO-VA-144HZ-1MS","SAMSUNG", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgMonitores%2F2.png?alt=media&token=d469c0a7-ef3d-426a-81ae-0401ccb7bda8",true),
            Producto("PR019",4,"F24G35TFW","VA–144HZ-1MS","SAMSUNG", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgMonitores%2F3.png?alt=media&token=fbc9ba0b-f45d-47d9-9929-97813c22a7af",true),
            Producto("PR020",4,"LS34J550W","ULTRA WQHD-VA–75HZ","SAMSUNG", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgMonitores%2F4.png?alt=media&token=b116b6bf-eb1a-4b4f-adfb-f6bffa79f4dc",true),
            Producto("PR021",4,"C49G95TSS","CURVO–VA–240HZ–1MS","SAMSUNG", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgMonitores%2F5.png?alt=media&token=222e82b4-ea33-4eef-9c2f-665efc9152bb",true),

            Producto("PR022",5,"910005790","Gamer alambrico","Logitech", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgMouse%2F1.png?alt=media&token=e898d39a-09d4-4382-8636-82c5e0c914ec",true),
            Producto("PR023",5,"910005469","Gamer alambrico","Logitech", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgMouse%2F2.png?alt=media&token=6aabbea0-5621-426f-a179-c9eb9c17de8d",true),
            Producto("PR024",5,"910005565","Gamer inalambrico","Logitech", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgMouse%2F3.png?alt=media&token=50e18f7c-13fd-4ea1-8cf9-6d8bfbcae1ca",true),
            Producto("PR025",5,"RZ01-02120100-R3U1","Gamer inalambrico","Razer", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgMouse%2F4.png?alt=media&token=b41a8301-8614-44a6-b5c4-d2cdeb425a94",true),
            Producto("PR026",5,"RZ01-02410100-R3U1","Gamer inalambrico","Razer", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgMouse%2F5.png?alt=media&token=7294c0ad-ff43-4fde-9922-f46b79fc96f0",true),

            Producto("PR027",6,"AP-KB750","KIRIN 80 PLUS BRONZE","ANTRYX", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgFuentes%2F1.png?alt=media&token=a2090531-7ae5-44be-bc0d-70024b21aba4",true),
            Producto("PR028",6,"MPE8501AC","MWE 850W V2","COOLER MASTER", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgFuentes%2F2.png?alt=media&token=71329961-0030-4f67-9543-88a2cd476745",true),
            Producto("PR029",6,"90YE00D1B","TUF GAMING","ASUS", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgFuentes%2F3.png?alt=media&token=931876cc-6070-4725-8954-dec156f20004",true),


            Producto("PR030",7,"920-008300","Switch Blue Teclado 100%","Logitech", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgTeclado%2F1.png?alt=media&token=95a550d9-ad6b-4f37-bc91-f80c61724ce3",true),
            Producto("PR031",7,"920-009322","Switch Blue Teclado 100%","Logitech", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgTeclado%2F2.png?alt=media&token=d6137960-1c53-4f44-a6b9-a442fc49b49f",true),
            Producto("PR032",7,"920-009388","Switch Brown Teclado 80%","Logitech", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgTeclado%2F3.png?alt=media&token=a5b7414c-1435-4dca-bb68-61118f888d49",true),
            Producto("PR033",7,"920-010533","Switch Brown Teclado 80%","Logitech", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgTeclado%2F4.png?alt=media&token=baa1fd55-301a-4f77-8cfc-fba3ce569bbb",true),
            Producto("PR034",7,"CH-9104110-SP","Switch Red Teclado 100%","Corsair", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgTeclado%2F5.png?alt=media&token=3e0c0714-c4b7-4372-84bf-1003eb0ea7f1",true),

            Producto("PR035",8,"ST1000DM0","Barracuda Interno","SEAGATE", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgAlmacenamiento%2F1.png?alt=media&token=b46977ec-f980-4bf1-b13e-14e359483302",true),
            Producto("PR036",8,"WD100EZEX","Blue Interno","WESTERN DIGITAL", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgAlmacenamiento%2F2.png?alt=media&token=aac883e0-8d7c-4cef-9370-b9c73ad520fd",true),
            Producto("PR037",8,"MQ01ABDu0","Red Interno","TOSHIBA", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgAlmacenamiento%2F3.png?alt=media&token=87be74fc-0fc3-4b50-90f1-85da11916001",true),
            Producto("PR038",8,"HDTB410XK3AA","Canvio Basic Externo","TOSHIBA", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgAlmacenamiento%2F4.png?alt=media&token=7610c641-f3af-4c14-b1d2-763df3cdef1a",true),
            Producto("PR039",8,"HDTB420XK3AA","Canvio Basic Externo","TOSHIBA", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgAlmacenamiento%2F5.png?alt=media&token=1274f36a-81b2-49e6-8fe8-3ac89bf993a4",true),
            Producto("PR040",8,"HDTB440XK3CA","Canvio Basic Externo","TOSHIBA", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgAlmacenamiento%2F6.png?alt=media&token=c501a490-23d2-4634-a3bc-001730ee31b4",true),

            Producto("PR041",9,"DP-N146-N9BKL","Para Laptop de 15.6 pulgadas Reclinable","Deep Cool", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgSoporte%2F1.png?alt=media&token=5e48943b-3321-49ab-b106-6686d1f6d0f3",true),
            Producto("PR042",9,"DP-N24N-N8BK","Para Laptop de 17 pulgadas Estatico","Deep Cool", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgSoporte%2F2.png?alt=media&token=c96302e5-b987-486e-8c12-f7e8b0d20aca",true),
            Producto("PR043",9,"DP-N222-N65BK","Para Laptop de 17 pulgadas Estatico","Deep Cool", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgSoporte%2F3.png?alt=media&token=af81ba9d-d878-4296-9f07-ab7a286b1954",true),
            Producto("PR044",9,"DP-N248-N9EBK","Para Laptop de 17 pulgadas Reclinable","Deep Cool", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgSoporte%2F4.png?alt=media&token=6b504852-6452-414d-b48e-2aeb8f867caf",true),
            Producto("PR045",9,"DP-N222-N80RGB","Para Laptop de 17 pulgadas Reclinable","Deep Cool", imagenUrl = "https://firebasestorage.googleapis.com/v0/b/t5gl-2022-2-cab80.appspot.com/o/imgSoporte%2F5.png?alt=media&token=24b2bbf5-d94f-4b76-a9fc-0a69f7bcbc49",true),

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