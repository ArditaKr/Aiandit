package com.arditakrasniqi.aiandit.presentation.ui.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.arditakrasniqi.aiandit.R
import com.arditakrasniqi.aiandit.data.model.requests.LoginRequestModel
import com.arditakrasniqi.aiandit.databinding.FragmentLoginBinding
import com.arditakrasniqi.aiandit.presentation.utils.DataState
import com.arditakrasniqi.aiandit.presentation.utils.observeOnce
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel by viewModels<LoginViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleClickListeners()
    }

    private fun handleClickListeners() {
//        "email" : "tasktest@catch.com",
//        "password" : "qwer1234"
        binding.loginBtn.setOnClickListener {
            val email = binding.eMail.text.toString()
            val psw = binding.passwords.text.toString()

            if (email.isNullOrEmpty() || psw.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Empty fields are not allowed!", Toast.LENGTH_SHORT).show()
            }else{
                loginViewModel.login(LoginRequestModel(email,psw))
            }
        }
        loginViewModel.userLogin.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {
                    Log.d("TAG", "observeData: loading")
                }
                is DataState.Success -> {
                    loginViewModel.saveTokenAndId(it.data?.token,it.data?.user?.id)
                    findNavController().navigate(R.id.action_loginFragment_to_nav_home)
                }
                is DataState.Error -> {
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}