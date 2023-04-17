package com.shaban.clothes4weather.ui

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.shaban.clothes4weather.databinding.FragmentOnboardingBinding
import com.shaban.clothes4weather.ui.base.BaseFragment
import com.shaban.clothes4weather.utils.Constants
import com.shaban.clothes4weather.utils.SharedPreferencesUtil
import com.shaban.clothes4weather.utils.replaceFragment

class OnboardingFragment : BaseFragment<FragmentOnboardingBinding>() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val homeFragment: HomeFragment = HomeFragment()

    override val TAG: String
        get() = this::class.simpleName.toString()

    override fun getViewBinding(): FragmentOnboardingBinding =
        FragmentOnboardingBinding.inflate(layoutInflater)

    override fun setup() {
        SharedPreferencesUtil.initPreferencesUtil(requireContext())
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())
        addCallBacks()
    }

    private fun addCallBacks() {
        binding.getLocationButton.setOnClickListener {
            getCurrentLocation()
        }
    }

    private fun checkPermissions(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun getCurrentLocation() {

        if (checkPermissions()) {
            if (isLocationEnabled()) {

                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermission()
                    return
                }
                fusedLocationProviderClient.lastLocation.addOnCompleteListener {
                    val location: Location? = it.result
                    if (location == null)
                        Toast.makeText(requireContext(), "Null Received!!", Toast.LENGTH_SHORT)
                            .show()
                    else {
                        Toast.makeText(requireContext(), "Get Success", Toast.LENGTH_SHORT).show()
                        log("${location.latitude} + ${location.longitude}")

                        SharedPreferencesUtil.latitude = location.latitude.toFloat()
                        SharedPreferencesUtil.longitude = location.longitude.toFloat()

                        replaceFragment(homeFragment)
                    }
                }
            } else {
                Toast.makeText(context, "Granted!!", Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            Constants.PERMISSION_REQUEST_ACCESS_LOCATION
        )
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == Constants.PERMISSION_REQUEST_ACCESS_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "Granted!!", Toast.LENGTH_SHORT).show()
                getCurrentLocation()
            } else
                Toast.makeText(requireContext(), "Denied!!", Toast.LENGTH_SHORT).show()
        }
    }
}