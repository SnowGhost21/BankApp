package com.diegocunha.bankapp.model.repository.sharedpreference

import android.content.SharedPreferences
import com.diegocunha.bankapp.model.data.Account
import com.diegocunha.bankapp.model.repository.BankStorageRepository
import com.google.gson.Gson

class BankPreferenceRepository(private val preference: SharedPreferences,
                               private val gson: Gson) : BankStorageRepository {

    companion object {
        const val PREF_PROFILE = "profile"
    }

    override fun getProfile(): Account? {
        val profile = preference.getString(PREF_PROFILE, null)
        if (profile == null) {
            return null
        } else {
            return stringToProfile(profile)
        }
    }

    override fun saveProfile(account: Account) {
        val profile = profileToString(account)
        preference.edit()
                .putString(PREF_PROFILE, profile)
                .apply()
    }

    private fun profileToString(account: Account): String {
        return gson.toJson(account)
    }

    private fun stringToProfile(account: String): Account {
        return gson.fromJson(account, Account::class.java)
    }
}