package com.diegocunha.bankapp.model.repository

import com.diegocunha.bankapp.model.data.Account

interface BankStorageRepository {

    fun getProfile(): Account?

    fun saveProfile(account: Account)
}