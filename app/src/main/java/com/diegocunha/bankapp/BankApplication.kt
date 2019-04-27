package com.diegocunha.bankapp

import android.app.Application
import com.diegocunha.bankapp.dependency.appModule
import org.koin.android.ext.android.startKoin

class BankApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule))
    }
}