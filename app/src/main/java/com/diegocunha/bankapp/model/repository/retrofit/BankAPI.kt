package com.diegocunha.bankapp.model.repository.retrofit

import com.diegocunha.bankapp.model.data.RequestLogin
import com.diegocunha.bankapp.model.data.ResponseLogin
import com.diegocunha.bankapp.model.data.StatementResponse
import io.reactivex.Single
import retrofit2.http.*

interface BankAPI {

    @POST("login")
    fun login(@Body postLogin: RequestLogin): Single<ResponseLogin>

    @GET("statements/{userId}")
    fun getListOfStatments(@Path("userId") userId: String): Single<List<StatementResponse>>
}