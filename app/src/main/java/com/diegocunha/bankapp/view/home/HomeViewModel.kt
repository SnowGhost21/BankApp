package com.diegocunha.bankapp.view.home

import androidx.lifecycle.ViewModel
import com.diegocunha.bankapp.model.repository.BankRepository
import com.diegocunha.bankapp.model.repository.BankStorageRepository

class HomeViewModel(private val networkRepository: BankRepository,
                    private val storageRepository: BankStorageRepository): ViewModel() {
}