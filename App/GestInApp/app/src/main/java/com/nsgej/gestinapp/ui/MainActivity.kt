package com.nsgej.gestinapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.nsgej.gestinapp.databinding.ActivityMainBinding
import com.nsgej.gestinapp.viewmodel.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val loginViewModel by viewModels<LoginViewModel>()

    private val activityViewModel by viewModels<ActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

 /*       val navController = findNavController(R.id.nav_host_fragment)


        activityViewModel.getStatus()

        activityViewModel.onSession.observe(this) {
            loginViewModel.usuarioMutable.observe(this) { usuario ->
                if(usuario.isNotEmpty()){
                    if (it) {
                        navController.navigate(R.id.bienvenidoFragment)
                    }
                }else{
                    activityViewModel.clearData()
                }
            }
        }*/

    }




}

