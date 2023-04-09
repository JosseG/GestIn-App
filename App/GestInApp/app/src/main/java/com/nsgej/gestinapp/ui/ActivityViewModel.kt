package com.nsgej.gestinapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nsgej.gestinapp.prefs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor() : ViewModel() {

    private val _onSession = MutableLiveData<Boolean>()

    val onSession: LiveData<Boolean> = _onSession


    fun setData(id: String) {

        prefs.stringPref = id

    }

    fun clearData() {
        prefs.stringPref = null
    }

    fun getStatus() {
        _onSession.value = !(prefs.stringPref == null || prefs.stringPref!!.isEmpty())
    }


}