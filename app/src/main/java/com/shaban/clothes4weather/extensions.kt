package com.shaban.clothes4weather

import androidx.fragment.app.Fragment

fun Fragment.replaceFragment(fragment: Fragment) {
    val fragmentManager = requireActivity().supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()

    fragmentTransaction.replace(R.id.fragmentContainerView, fragment).addToBackStack(null)
    fragmentTransaction.commit()
}