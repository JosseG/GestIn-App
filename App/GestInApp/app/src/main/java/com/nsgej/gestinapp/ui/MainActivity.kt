package com.nsgej.gestinapp.ui

import android.app.Activity
import android.content.DialogInterface.BUTTON_POSITIVE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var dialog: AlertDialog? = null;

    val db = Firebase.firestore

    private val REQUEST_CODE = 11


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Inicio", "Empezandooo")
        val appUpdateManager = AppUpdateManagerFactory.create(this.applicationContext)
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo


        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val activityResult =
            registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
                when (result.resultCode) {
                    Activity.RESULT_OK -> {
                        Log.i("UPDATE TEST", "Todo ok");
                    }
                    else -> {
                        Log.i("UPDATE TEST", "No se completó")
                    }
                }
            }


        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && appUpdateInfo.isUpdateTypeAllowed(
                    AppUpdateType.IMMEDIATE
                )
            ) {
                try {
                    appUpdateManager.startUpdateFlowForResult(
                        appUpdateInfo,
                        activityResult,
                        AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE)
                            .setAllowAssetPackDeletion(true)
                            .build()
                    )
                } catch (e: Exception) {
                    Log.i("ERROR LISTENER UPDATE", e.toString())
                }
            }
        }

        db.collection("state")
            .addSnapshotListener { value, error ->
                if (value != null) {
                    for (document in value.documents) {

                        if (document.data?.get("isActive") == true) {

                            dismissUnderMaintenanceDialog()

                        } else {
                            showUnderMaintenanceDialog("Está en mantenimiento")
                        }
                    }
                } else {
                    showUnderMaintenanceDialog("Existe un error $error")
                }
            }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            Toast.makeText(this, "Empezando descarga", Toast.LENGTH_SHORT).show()
            if (resultCode != RESULT_OK) {
                Log.i("Error update", "La actualización falló ".plus(resultCode))
            }
        }
    }


    private fun showUnderMaintenanceDialog(msj: String) {
        if (dialog == null) {
            dialog = AlertDialog.Builder(this, R.style.MaterialAlertDialog__Center).create()
            dialog!!.setCancelable(false)
            dialog!!.setButton(
                BUTTON_POSITIVE,
                "OK"
            ) { dialog, which ->
                dialog.dismiss()
                super.finish()
            }

        }
        dialog!!.setMessage(msj)

        if (!this.isFinishing) {
            dialog!!.show()
        }
    }

    private fun dismissUnderMaintenanceDialog() {
        if (dialog != null && dialog!!.isShowing) dialog!!.dismiss()
    }

}


