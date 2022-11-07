package com.nsgej.gestinapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsgej.gestinapp.prefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor() : ViewModel() {

    private val _onSession = MutableLiveData<Boolean>()

    val onSession: LiveData<Boolean> = _onSession
/*

    private val _cantidadProMut = MutableLiveData<Int>()

    val cantidadProMut: LiveData<Int> = _cantidadProMut
*/


    fun setData(id: String) {

        prefs.stringPref = id
    }
/*
    fun regi(inventario : Inventario){
        viewModelScope.launch {

            inventariore.registra(inventario)
            _cantidadProMut.value = inventario.cantidad

        }
    }*/

    fun clearData() {
        prefs.stringPref = null
    }

    fun getStatus() {
        _onSession.value = !(prefs.stringPref == null || prefs.stringPref!!.isEmpty())
    }

}