package com.diegocunha.bankapp.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.diegocunha.bankapp.databinding.FragmentLoginBinding
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.isPasswordValid.observe(this, Observer {
            it?.let { isValid ->
                if (!isValid) {
                    binding.passwordEditText.error = ""
                }
            }
        })

        viewModel.isUserNameValid.observe(this, Observer {
            it?.let { isValid ->
                if (!isValid) {
                    binding.userNameEditText.error = ""
                }
            }
        })

        binding.loginButton.setOnClickListener {
            val userName = binding.userNameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            viewModel.login(userName, password)
        }



        return binding.root
    }
}