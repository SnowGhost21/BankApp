package com.diegocunha.bankapp.model.repository.retrofit

import com.diegocunha.bankapp.model.data.ResponseLogin
import com.diegocunha.bankapp.model.data.StatementResponse
import io.reactivex.Single
import retrofit2.http.*

interface BankAPI {

    @FormUrlEncoded
    @POST("login")
    fun login(@Field("login") login: String, @Field("password") password: String): Single<ResponseLogin>

    @GET("statements/{userId}")
    fun getListOfStatments(@Path("userId") userId: String): Single<List<StatementResponse>>
}