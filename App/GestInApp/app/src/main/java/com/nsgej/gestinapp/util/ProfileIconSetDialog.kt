package com.nsgej.gestinapp.util

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.nsgej.gestinapp.databinding.DialogIconProfileSetBinding
import com.nsgej.gestinapp.viewmodel.empleado.EmpleadoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileIconSetDialog(
    private val onSubmitClickListener: (String) -> Unit
) : DialogFragment() {

/*    lateinit var listener: OnCargoSelected*/
    private var _binding: DialogIconProfileSetBinding? = null
    // This property is only valid between onCreateDialog and
    // onDestroyView.
    private val binding get() = _binding!!
/*    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = parentFragmentManager as OnCargoSelected
    }*/

    @SuppressLint("InflateParams", "UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var cod = "0";

        return activity?.let {

             val empleadoViewModel by viewModels<EmpleadoViewModel>()
            _binding = DialogIconProfileSetBinding.inflate(requireActivity().layoutInflater)
            val builder = AlertDialog.Builder(it)
            // Get the layout inflate
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
                cod = "C00001"
                binding.btnAdministrador.colorFilter = ColorMatrixColorFilter(array)
                binding.btnAlmacenero.colorFilter = null
                binding.btnAsistenteVentas.colorFilter = null
            }

            binding.btnAsistenteVentas.setOnClickListener {
                cod = "C00002"

                binding.btnAsistenteVentas.colorFilter = ColorMatrixColorFilter(array)
                binding.btnAlmacenero.colorFilter = null
                binding.btnAdministrador.colorFilter = null
            }

            binding.btnAlmacenero.setOnClickListener {
                cod = "C00003"
                binding.btnAlmacenero.colorFilter = ColorMatrixColorFilter(array)
                binding.btnAsistenteVentas.colorFilter = null
                binding.btnAdministrador.colorFilter = null
            }





            builder.setView(binding.root)
                .setPositiveButton("Aceptar"
                ) { dialog, id ->
                    onSubmitClickListener.invoke(cod)
                    Log.i("final",cod)
                }
                .setNegativeButton("Cancelar"
                ) { dialog, id ->
                    getDialog()?.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}