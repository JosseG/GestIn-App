package com.nsgej.gestinapp.ui.login

import com.nsgej.gestinapp.domain.model.Usuario

data class LoginResult(
    val success: Usuario? = null,
    val error: Int? = null
)