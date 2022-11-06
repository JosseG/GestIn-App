package com.nsgej.gestinapp

import android.app.Application
import com.nsgej.gestinapp.data.bd.module.Preferencias
import dagger.hilt.android.HiltAndroidApp


val prefs: Preferencias by lazy {
    MiAplicacion.prefs!!
}

@HiltAndroidApp
class MiAplicacion : Application() {


    companion object {
        var prefs: Preferencias? = null
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Preferencias(applicationContext)
    }

}