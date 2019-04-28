package com.diegocunha.bankapp.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.diegocunha.bankapp.model.data.RequestLogin
import com.diegocunha.bankapp.model.data.ResponseLogin
import com.diegocunha.bankapp.model.repository.BankRepository
import com.diegocunha.bankapp.model.repository.BankStorageRepository
import com.diegocunha.bankapp.view.commom.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.regex.Pattern

class LoginViewModel(
    private val repository: BankRepository,
    private val storageRepository: BankStorageRepository
) : BaseViewModel() {

    private var loginDisposable: Disposable? = null
    private val _isPasswordValid = MutableLiveData<Boolean>()
    val isPasswordValid: LiveData<Boolean> = _isPasswordValid

    private val _isUserNameValid = MutableLiveData<Boolean>()
    val isUserNameValid: LiveData<Boolean> = _isUserNameValid

    fun login(userName: String, password: String) {
        val isUserValid = isUserValid(userName)
        val isPasswordValid = isPasswordValid(password)

        if (!isUserValid || !isPasswordValid) {
            return
        }


        val postLogin = RequestLogin(userName, password)
        loginDisposable = repository.login(postLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { setIsLoading(true) }
            .doOnSuccess {
                if (it.error?.message == null && it.accountInfo != null) {
                    storageRepository.saveProfile(it.accountInfo)
                    setIsSuccess(true)
                } else {
                    setMessageError(it.error)
                    setIsError(true)
                }
            }
            .doOnError {
                setIsError(true)
                createMessageError(it)
            }
            .onErrorReturnItem(ResponseLogin(null, null))
            .doAfterTerminate { setIsLoading(false) }
            .subscribe()
    }

    private fun isPasswordValid(password: String?): Boolean {
        if (password.isNullOrEmpty()) {
            _isPasswordValid.postValue(false)
            return false
        }

        val specialCharacterPattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE)
        val upperCasePattern = Pattern.compile("[A-Z ]")
        val digitCasePattern = Pattern.compile("[0-9 ]")

        var isValid = specialCharacterPattern.matcher(password).find()
        isValid = isValid and upperCasePattern.matcher(password).find()
        isValid = isValid and digitCasePattern.matcher(password).find()

        _isPasswordValid.postValue(isValid)

        return isValid
    }

    private fun isUserValid(userName: String): Boolean {
        val isValid = userName.isNotEmpty()
        _isUserNameValid.postValue(isValid)

        return isValid
    }

    override fun onCleared() {
        super.onCleared()
        loginDisposable?.dispose()
    }
}