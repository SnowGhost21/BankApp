package com.diegocunha.bankapp.view.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegocunha.bankapp.R
import com.diegocunha.bankapp.model.repository.BankStorageRepository

class SplashViewModel(private val repository: BankStorageRepository) : ViewModel() {


    private val _route = MutableLiveData<Int>()
    val route: LiveData<Int> = _route

    init {
        initRouter()
    }

    private fun initRouter() {
        _route.postValue(
                if (repository.getProfile() == null)
                    R.id.action_splashFragment_to_loginFragment
                else
                    R.id.action_splashFragment_to_homeFragment)
    }
}