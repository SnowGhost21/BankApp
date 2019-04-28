package com.diegocunha.bankapp.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.diegocunha.bankapp.R
import com.diegocunha.bankapp.databinding.FragmentLoginBinding
import com.diegocunha.bankapp.view.commom.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.isPasswordValid.observe(this, Observer {
            it?.let { isValid ->
                if (!isValid) {
                    binding.passwordEditText.error = getString(R.string.password_error)
                }
            }
        })

        viewModel.isUserNameValid.observe(this, Observer {
            it?.let { isValid ->
                if (!isValid) {
                    binding.userNameEditText.error = getString(R.string.user_error)
                }
            }
        })

        viewModel.errorMessage.observe(this, Observer {
            it?.let { error -> showErrorMessage(error) }
        })

        binding.loginButton.setOnClickListener {
            val userName = binding.userNameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            hideKeyboard()
            viewModel.login(userName, password)
        }

        return binding.root
    }
}