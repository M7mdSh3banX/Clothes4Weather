package com.shaban.clothes4weather

import com.shaban.clothes4weather.databinding.FragmentOnboarding1Binding

class OnboardingFragment1 : BaseFragment<FragmentOnboarding1Binding>() {
    override val TAG: String
        get() = this::class.simpleName.toString()

    override fun getViewBinding(): FragmentOnboarding1Binding =
        FragmentOnboarding1Binding.inflate(layoutInflater)

    override fun setup() {
        TODO("Not yet implemented")
    }
}