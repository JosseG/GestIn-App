package com.nsgej.gestinapp.util

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.nsgej.gestinapp.databinding.DialogIconProfileSetBinding

class ProfileIconSetDialog : DialogFragment() {


    private var _binding: DialogIconProfileSetBinding? = null
    // This property is only valid between onCreateDialog and
    // onDestroyView.
    private val binding get() = _binding!!
    @SuppressLint("InflateParams", "UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var cod = 0;

        return activity?.let {
            _binding = DialogIconProfileSetBinding.inflate(requireActivity().layoutInflater)
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
          /*  val inflater = requireActivity().layoutInflater*/

/*
            val binding = inflater.inflate(
                R.layout.dialog_icon_profile_set, null)


*/

            var array : FloatArray = floatArrayOf(
                 -1F, 0F, 0F, 0F, 255F,
                    0F, -1F, 0F, 0F, 255F,
                    0F, 0F, -1F, 0F, 255F,
                    0F, 0F, 0F, 1F, 0F
            )

            binding.btnAdministrador.setOnClickListener {
                Log.i("saludo","perfil")

                cod = 100
                binding.btnAdministrador.colorFilter = ColorMatrixColorFilter(array)
                binding.btnAlmacenero.colorFilter = null
                binding.btnAsistenteVentas.colorFilter = null
            }

            binding.btnAlmacenero.setOnClickListener {
                Log.i("saludo","reporte")
                cod = 200
                binding.btnAlmacenero.colorFilter = ColorMatrixColorFilter(array)
                binding.btnAsistenteVentas.colorFilter = null
                binding.btnAdministrador.colorFilter = null
            }

            binding.btnAsistenteVentas.setOnClickListener {
                Log.i("saludo","transaccion")
                cod = 300

                binding.btnAsistenteVentas.colorFilter = ColorMatrixColorFilter(array)
                binding.btnAlmacenero.colorFilter = null
                binding.btnAdministrador.colorFilter = null
            }



            builder.setView(binding.root)
                // Add action buttons
                .setPositiveButton("Aceptar"
                ) { dialog, id ->
                    // sign in the user ...

                    Log.i("final",cod.toString())


                }
                .setNegativeButton("Cancelar"
                ) { dialog, id ->
                    getDialog()?.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}