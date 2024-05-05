package com.bella.mykpuapps.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bella.mykpuapps.R
import com.bella.mykpuapps.databinding.ActivityMainBinding
import com.bella.mykpuapps.entry.FormEntryActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnInfoPemilu.setOnClickListener {
            val intent = Intent(this, InfoPemiluActivity::class.java)
            startActivity(intent)
        }

        binding.btnEntry.setOnClickListener {
            val intent = Intent(this, FormEntryActivity::class.java)
            startActivity(intent)
        }

        binding.btnInfoPemilih.setOnClickListener {
            val intent = Intent(this, InfoDaftarPemilihActivity::class.java)
            startActivity(intent)
        }

        binding.btnKeluar.setOnClickListener {
            finish()
        }
    }
}