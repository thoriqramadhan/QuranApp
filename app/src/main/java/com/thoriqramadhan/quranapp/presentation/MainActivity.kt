 package com.thoriqramadhan.quranapp.presentation

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.location.LocationServices
import com.thoriqramadhan.quranapp.R
import com.thoriqramadhan.quranapp.databinding.ActivityMainBinding
import com.thoriqramadhan.quranapp.utils.LOCATION_PERMISSION_REQ_CODE

 class MainActivity : AppCompatActivity() {
     private var _binding: ActivityMainBinding? = null
     private val binding get() = _binding as ActivityMainBinding

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         _binding = ActivityMainBinding.inflate(layoutInflater)
         setContentView(binding.root)
         getUserLocation()

         val bottomNavView = binding.navBottomView
         val navHostFragment =
             supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
         val navController = navHostFragment.navController
         bottomNavView.setupWithNavController(navController)
     }

     private fun getUserLocation() {
         if (checkLocationPermission()) {
             if (isLocationOn()) {
                 val fusedLocation = LocationServices.getFusedLocationProviderClient(this)
                 fusedLocation.lastLocation
/*
                fusedLocation.lastLocation.addOnCompleteListener {
                    if (it.result != null) {
                        val geocoder = Geocoder(this, Locale.getDefault())
                        geocoder.getFromLocation(
                            it.result.latitude,
                            it.result.longitude,
                            1
                        ) { listAddress ->
                            val city = listAddress[0].subAdminArea
                            val resultOfCity = city.split(" ")
                            Snackbar.make(binding.root, resultOfCity[1], Snackbar.LENGTH_INDEFINITE)
                                .show()
                        }
                    } else {
                        Toast.makeText(this, "Sorry something wrong", Toast.LENGTH_SHORT).show()
                    }
                }*/
             } else {
                 Toast.makeText(this, "Please turn on your location", Toast.LENGTH_SHORT).show()
                 val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                 startActivity(intent)
             }
         } else {
             requestLocationPermission()
         }
     }
     private fun isLocationOn(): Boolean {
         val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
         return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) &&
                 locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
     }
     private fun requestLocationPermission() {
         ActivityCompat.requestPermissions(
             this,
             arrayOf(
                 Manifest.permission.ACCESS_FINE_LOCATION,
                 Manifest.permission.ACCESS_COARSE_LOCATION
             ),
             LOCATION_PERMISSION_REQ_CODE
         )
     }
     private fun checkLocationPermission(): Boolean {
         if (ActivityCompat.checkSelfPermission(
                 this,
                 Manifest.permission.ACCESS_FINE_LOCATION
             ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                 this,
                 Manifest.permission.ACCESS_COARSE_LOCATION
             ) != PackageManager.PERMISSION_GRANTED
         ) {
             // TODO: Consider calling
             //    ActivityCompat#requestPermissions
             // here to request the missing permissions, and then overriding
             //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
             //                                          int[] grantResults)
             // to handle the case where the user grants the permission. See the documentation
             // for ActivityCompat#requestPermissions for more details.
             return false
         }
         return true
     }
     override fun onRequestPermissionsResult(
         requestCode: Int,
         permissions: Array<out String>,
         grantResults: IntArray
     ) {
         super.onRequestPermissionsResult(requestCode, permissions, grantResults)
         if (requestCode == LOCATION_PERMISSION_REQ_CODE) {
             if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                 getUserLocation()
             } else {
                 Toast.makeText(this, "Need Permission Location.", Toast.LENGTH_SHORT).show()
             }
         } else {
             getUserLocation()
         }
     }
 }
