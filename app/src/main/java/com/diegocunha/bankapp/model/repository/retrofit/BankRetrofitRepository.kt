package com.diegocunha.bankapp.model.repository.retrofit

import com.diegocunha.bankapp.model.data.RequestLogin
import com.diegocunha.bankapp.model.data.ResponseLogin
import com.diegocunha.bankapp.model.data.StatementResponse
import com.diegocunha.bankapp.model.repository.BankRepository
import com.diegocunha.bankapp.model.repository.BankStorageRepository
import io.reactivex.Single

class BankRetrofitRepository(private val api: BankAPI,
                             private val storageRepository: BankStorageRepository) : BankRepository {

    override fun login(postLogin: RequestLogin): Single<ResponseLogin> {
        return api.login(postLogin.user, postLogin.password)
    }

    override fun getListOfStatements(): Single<List<StatementResponse>> {
        val userId = storageRepository.getProfile()?.userId.toString()
        return api.getListOfStatments(userId)
    }
}