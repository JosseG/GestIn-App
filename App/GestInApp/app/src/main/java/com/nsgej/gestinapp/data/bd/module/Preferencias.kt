package com.nsgej.gestinapp.data.bd.module

import android.content.Context
import android.content.SharedPreferences



class Preferencias (context: Context) {

    private val prefString = "Usuario"

    private val preferences: SharedPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)!!

    var stringPref: String?
        get() = preferences.getString(prefString, null)
        set(value) = preferences.edit().putString(prefString, value).apply()
}