package com.diegocunha.bankapp.model.repository

import com.diegocunha.bankapp.model.data.RequestLogin
import com.diegocunha.bankapp.model.data.ResponseLogin
import com.diegocunha.bankapp.model.data.StatementResponse
import io.reactivex.Single

interface BankRepository {

    fun login(postLogin: RequestLogin): Single<ResponseLogin>

    fun getListOfStatements(): Single<List<StatementResponse>>
}