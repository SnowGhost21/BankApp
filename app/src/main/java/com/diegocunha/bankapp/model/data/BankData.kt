package com.diegocunha.bankapp.model.data

import com.google.gson.annotations.SerializedName

data class RequestLogin(@SerializedName("user") val user: String,
                        @SerializedName("password") val password: String
)

data class ResponseLogin(
        @SerializedName("userAccount") val accountInfo: Account?,
        @SerializedName("error") val error: Error?
)

data class Error(@SerializedName("code") val code: Int,
                 @SerializedName("message") val message: String?
)

data class Account(@SerializedName("userId") val userId: Int,
                   @SerializedName("name") val userName: String,
                   @SerializedName("bankAccount") val accountNumber: String,
                   @SerializedName("agency") val agency: String,
                   @SerializedName("balance") val balance: Double
)

data class StatementResponse(@SerializedName("statementList") val statementList: List<Statement>)

data class Statement(@SerializedName("title") val title: String,
                     @SerializedName("desc") val description: String,
                     @SerializedName("date") val date: String,
                     @SerializedName("value") val value: Double
)