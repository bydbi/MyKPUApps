package com.bella.mykpuapps.entry

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.bella.mykpuapps.db.DataPemilih
import com.bella.mykpuapps.repository.DaftarPemilihRep

class FormEntryViewModel (application: Application) : AndroidViewModel(application) {

    private val mDataPemilihRepository: DaftarPemilihRep = DaftarPemilihRep(application)

    fun insert(datapemilih: DataPemilih) {
        mDataPemilihRepository.insert(datapemilih)
    }

    fun update(datapemilih: DataPemilih) {
        mDataPemilihRepository.update(datapemilih)
    }

    fun getDataPemilihByNIK(nik: String): LiveData<DataPemilih> {
        return mDataPemilihRepository.getDataPemilihByNIK(nik)
    }

    fun delete(datapemilih: DataPemilih) {
        mDataPemilihRepository.delete(datapemilih)
    }

}