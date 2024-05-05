package com.bella.mykpuapps.entry

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.bella.mykpuapps.R
import com.bella.mykpuapps.databinding.ActivityMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var savedLocation: LatLng? = null
    private var alamatPalsu: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this@MapsActivity)

        binding.btnSimpanLokasi.setOnClickListener {
            if (savedLocation != null) {
                val intent = Intent()
                intent.putExtra("latitude", savedLocation?.latitude)
                intent.putExtra("longitude", savedLocation?.longitude)
                intent.putExtra("alamat", alamatPalsu)
                Log.d("Alamat : ", alamatPalsu.toString())
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(
                    this@MapsActivity,
                    "Tidak ada data lokasi yang tersedia",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.btnZoomIn.setOnClickListener {
            zoomIn()
        }

        binding.btnZoomOut.setOnClickListener {
            zoomOut()
        }

        getMyLastLocation()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapClickListener { latLng ->
            savedLocation = latLng
            mMap.clear()
            mMap.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title("Lokasi Dipilih")
                    .draggable(true)
            )
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false -> {

                    getMyLastLocation()
                }
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false -> {
                    getMyLastLocation()
                }
                else -> {
                }
            }
        }

    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun getMyLastLocation() {
        if (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION) &&
            checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val geocoder = Geocoder(this@MapsActivity, Locale.getDefault())
                    val addressList: MutableList<Address>? =
                        geocoder.getFromLocation(location.latitude, location.longitude, 1)

                    if (addressList!!.isNotEmpty()) {
                        val streetAddress = addressList[0].getAddressLine(0)
                        savedLocation = LatLng(location.latitude, location.longitude)
                        alamatPalsu = streetAddress
                        showMarker(location)
                    } else {
                        Toast.makeText(
                            this@MapsActivity,
                            "Lokasi Tidak Ditemukan!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    savedLocation = LatLng(location.latitude, location.longitude)
                    showMarker(location)
                } else {
                    Toast.makeText(
                        this@MapsActivity,
                        "Lokasi tidak ditemukan. Coba lagi",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } else {
            requestPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    private fun showMarker(location: Location) {
        val currentLatLng = LatLng(location.latitude, location.longitude)
        mMap.addMarker(
            MarkerOptions()
                .position(currentLatLng)
                .title("Lokasi Terkini")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 17f))
    }

    private fun zoomIn() {
        mMap.animateCamera(CameraUpdateFactory.zoomIn())
    }

    private fun zoomOut() {
        mMap.animateCamera(CameraUpdateFactory.zoomOut())
    }
}