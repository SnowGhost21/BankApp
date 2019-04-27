package com.diegocunha.bankapp.dependency

import android.preference.PreferenceManager
import com.diegocunha.bankapp.BuildConfig
import com.diegocunha.bankapp.model.repository.BankRepository
import com.diegocunha.bankapp.model.repository.BankStorageRepository
import com.diegocunha.bankapp.model.repository.retrofit.BankAPI
import com.diegocunha.bankapp.model.repository.retrofit.BankRetrofitRepository
import com.diegocunha.bankapp.model.repository.sharedpreference.BankPreferenceRepository
import com.diegocunha.bankapp.view.home.HomeViewModel
import com.diegocunha.bankapp.view.login.LoginViewModel
import com.diegocunha.bankapp.view.splash.SplashViewModel
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    factory {

        val logging = HttpLoggingInterceptor()
        logging.level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build()
    }

    factory { GsonBuilder().create() }

    factory {
        Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(get()))
                .client(get())
                .build()
    }

    factory {
        val retrofit: Retrofit = get()
        retrofit.create(BankAPI::class.java)
    }

    factory { PreferenceManager.getDefaultSharedPreferences(get()) }

    single { BankRetrofitRepository(get(), get()) as BankRepository }

    single { BankPreferenceRepository(get(), get()) as BankStorageRepository }

    viewModel { SplashViewModel(get()) }

    viewModel { LoginViewModel(get(), get()) }

    viewModel { HomeViewModel(get(), get()) }

}