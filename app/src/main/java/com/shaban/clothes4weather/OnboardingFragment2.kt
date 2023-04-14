package com.shaban.clothes4weather

import com.shaban.clothes4weather.databinding.FragmentOnboarding2Binding

class OnboardingFragment2 : BaseFragment<FragmentOnboarding2Binding>() {
    override val TAG: String
        get() = this::class.simpleName.toString()

    override fun getViewBinding(): FragmentOnboarding2Binding =
        FragmentOnboarding2Binding.inflate(layoutInflater)

    override fun setup() {
        TODO("Not yet implemented")
    }
}