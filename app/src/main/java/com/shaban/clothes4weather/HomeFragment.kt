package com.shaban.clothes4weather

import com.shaban.clothes4weather.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val TAG: String
        get() = this::class.simpleName.toString()

    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)


    override fun setup() {

    }

}