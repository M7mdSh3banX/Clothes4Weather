package com.shaban.clothes4weather

import com.shaban.clothes4weather.databinding.FragmentOnboarding3Binding

class OnboardingFragment3 : BaseFragment<FragmentOnboarding3Binding>() {
    override val TAG: String
        get() = this::class.simpleName.toString()

    override fun getViewBinding(): FragmentOnboarding3Binding =
        FragmentOnboarding3Binding.inflate(layoutInflater)

    override fun setup() {
        TODO("Not yet implemented")
    }
}