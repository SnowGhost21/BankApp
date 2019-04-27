package com.diegocunha.bankapp.view.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.diegocunha.bankapp.R
import org.koin.android.viewmodel.ext.android.viewModel

class SplashFragment: Fragment() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel.route.observe(this, Observer {
            findNavController().navigate(it)
        })

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
}