package com.nsgej.gestinapp.util

import android.util.Log


fun getSequenceIdEmployee(id: String) : String{

    //prefix
    val prefix = "E";

    //numberOfDigitsInStringPrefixed
    var digitsStringPrefixed = 0

    //numberOfDigitsMaxReturn
    val digitsReturn = 6

    //numberOfCero
    var digitsCero = 0

    val new = id.removePrefix("E")
    var numberId = new.toInt()
    numberId++

    digitsStringPrefixed = numberId.toString().count{it.isDigit()}

    Log.i("Number",digitsStringPrefixed.toString())

    digitsCero = 5 - digitsStringPrefixed

    var ceros ="0".repeat(digitsCero)

    return prefix.plus(ceros).plus(numberId)

}