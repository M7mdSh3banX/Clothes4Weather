package com.shaban.clothes4weather.ui

import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import com.shaban.clothes4weather.R
import com.shaban.clothes4weather.databinding.FragmentHomeBinding
import com.shaban.clothes4weather.ui.base.BaseFragment
import com.shaban.clothes4weather.utils.SharedPreferencesUtil
import com.shaban.clothes4weather.viewModels.HomeViewModel


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    override val TAG: String
        get() = this::class.simpleName.toString()

    override fun getLayoutResId(): Int = R.layout.fragment_home

    override fun initView() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        SharedPreferencesUtil.initPreferencesUtil(requireContext())
        isNightMode()
    }

    private fun isNightMode() {
        if (SharedPreferencesUtil.isNightMode!!) {
            binding.switchMode.isChecked = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        binding.switchMode.setOnClickListener {
            if (SharedPreferencesUtil.isNightMode!!) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                SharedPreferencesUtil.isNightMode = false
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                SharedPreferencesUtil.isNightMode = true
            }
        }
    }
}