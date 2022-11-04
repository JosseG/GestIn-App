package com.nsgej.gestinapp.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
//lo comente  solo para que los demas puedan ver fragment , usted descomentelo es provisional
    /*companion object {
       fun newInstance() = LoginFragment()
   }

    private lateinit var viewModel: LoginViewModel*/
    private var _binding: FragmentLoginBinding?= null
    val binding get( )= _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container,false)
        return (binding.root)
    }

    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel

    }*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnIngresar.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_bienvenidoFragment)
        }

    }
    override fun onDestroy(){
        super.onDestroy()
        _binding=null
    }

}