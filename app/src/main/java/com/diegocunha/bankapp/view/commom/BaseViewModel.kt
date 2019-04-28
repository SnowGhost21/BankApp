package com.diegocunha.bankapp.view.commom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegocunha.bankapp.extensions.mutableLiveDataOf
import com.diegocunha.bankapp.model.data.Error

open class BaseViewModel : ViewModel() {

    private val _isLoading = mutableLiveDataOf(false)
    val isLoading: LiveData<Boolean> = _isLoading
    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error
    private val _success = mutableLiveDataOf(false)
    val success: LiveData<Boolean> = _success

    val _errorMessage = MutableLiveData<Error>()
    val errorMessage: LiveData<Error> = _errorMessage

    fun setIsLoading(value: Boolean) {
        _isLoading.postValue(value)
    }

    fun setIsError(value: Boolean) {
        _error.postValue(value)
    }

    fun setIsSuccess(value: Boolean) {
        _success.postValue(value)
    }

    fun setMessageError(error: Error?) {
        _errorMessage.postValue(error)
    }

    fun createMessageError(throwable: Throwable) {
        val error = Error(-1, throwable.message)
        setMessageError(error)
    }
}