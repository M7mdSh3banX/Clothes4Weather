package com.shaban.clothes4weather.utils

import androidx.fragment.app.Fragment
import com.shaban.clothes4weather.R

fun Fragment.replaceFragment(fragment: Fragment) {
    val fragmentManager = requireActivity().supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()

    fragmentTransaction.replace(R.id.fragmentContainerView, fragment).addToBackStack(null)
    fragmentTransaction.commit()
}