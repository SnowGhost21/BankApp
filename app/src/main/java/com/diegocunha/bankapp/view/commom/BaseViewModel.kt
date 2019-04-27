package com.diegocunha.bankapp.view.commom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegocunha.bankapp.extensions.mutableLiveDataOf

open class BaseViewModel: ViewModel() {

    private val _isLoading = mutableLiveDataOf(false)
    val isLoading: LiveData<Boolean> = _isLoading
    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error
    private val _success = mutableLiveDataOf(false)
    val success: LiveData<Boolean> = _success

    fun setIsLoading(value: Boolean) {
        _isLoading.postValue(value)
    }

    fun setIsError(value: Boolean) {
        _error.postValue(value)
    }

    fun setIsSuccess(value: Boolean) {
        _success.postValue(value)
    }
}