package com.bella.mykpuapps.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bella.mykpuapps.R
import com.bella.mykpuapps.databinding.ActivityInfoDaftarPemilihBinding
import com.bella.mykpuapps.helper.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class InfoDaftarPemilihActivity : AppCompatActivity() {
    private var _daftarDataPemilihBinding: ActivityInfoDaftarPemilihBinding? = null
    private val binding get() = _daftarDataPemilihBinding

    private lateinit var adapter: InfoPemilihAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _daftarDataPemilihBinding = ActivityInfoDaftarPemilihBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.title = "Daftar Data Pemilih"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val daftarDataPemilihViewModel = obtainViewModel(this@InfoDaftarPemilihActivity)

        daftarDataPemilihViewModel.getAllDataPemilih().observe(this) { datapemilihList ->
            if (datapemilihList != null && datapemilihList.isNotEmpty()) {
                adapter.setListDataPemilih(datapemilihList)
            } else {
                adapter.setListDataPemilih(emptyList())
                showNoDataSnackbar()
            }
        }

        adapter = InfoPemilihAdapter()

        binding?.rvDatapemilih?.layoutManager = LinearLayoutManager(this)
        binding?.rvDatapemilih?.setHasFixedSize(true)
        binding?.rvDatapemilih?.adapter = adapter

    }

    private fun showNoDataSnackbar() {
        val snackbar = Snackbar.make(
            binding?.root!!,
            "Tidak ada data saat ini",
            Snackbar.LENGTH_LONG
        )
        snackbar.show()
    }

    private fun obtainViewModel(activity: AppCompatActivity): InfoDaftarPemilihViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(InfoDaftarPemilihViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        _daftarDataPemilihBinding = null
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}