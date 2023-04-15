package com.shaban.clothes4weather.ui

import com.shaban.clothes4weather.data.source.RemoteDataSource
import com.shaban.clothes4weather.databinding.FragmentHomeBinding
import com.shaban.clothes4weather.ui.base.BaseFragment
import com.shaban.clothes4weather.utils.SharedPreferencesUtil


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val TAG: String
        get() = this::class.simpleName.toString()

    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)


    override fun setup() {
        RemoteDataSource.makeRequestUsingOKHTTP()
    }

}