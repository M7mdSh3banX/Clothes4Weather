package com.shaban.clothes4weather

import com.shaban.clothes4weather.databinding.FragmentOnboardingBinding

class OnboardingFragment : BaseFragment<FragmentOnboardingBinding>() {
    override val TAG: String
        get() = this::class.simpleName.toString()

    override fun getViewBinding(): FragmentOnboardingBinding =
        FragmentOnboardingBinding.inflate(layoutInflater)

    override fun setup() {
        TODO("Not yet implemented")
    }
}