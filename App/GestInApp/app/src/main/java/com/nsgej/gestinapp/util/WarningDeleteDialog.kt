package com.nsgej.gestinapp.util

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.databinding.DialogIconProfileSetBinding

class WarningDeleteDialog(
    private val onSubmitClickListener: (Boolean) -> Unit
) : DialogFragment() {



    @SuppressLint("InflateParams", "UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage("¿Está seguro de eliminarse?, será enviado a la pantalla de Login")
                .setPositiveButton("Aceptar"
                ) { dialog, id ->
                    // START THE GAME!
                    onSubmitClickListener.invoke(true)
                }
                .setNegativeButton("Cancelar"
                ) { dialog, id ->
                    getDialog()?.cancel()
                }
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


}