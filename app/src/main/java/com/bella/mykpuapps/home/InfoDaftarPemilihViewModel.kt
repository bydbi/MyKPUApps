package com.bella.mykpuapps.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bella.mykpuapps.db.DataPemilih
import com.bella.mykpuapps.repository.DaftarPemilihRep

class InfoDaftarPemilihViewModel (application: Application) : ViewModel() {

    private val mDataPemilihRepository: DaftarPemilihRep = DaftarPemilihRep(application)

    fun getAllDataPemilih(): LiveData<List<DataPemilih>> =
        mDataPemilihRepository.getAllDataPemilih()
}