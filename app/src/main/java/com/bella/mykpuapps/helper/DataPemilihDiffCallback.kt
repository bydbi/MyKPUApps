package com.bella.mykpuapps.helper

import androidx.recyclerview.widget.DiffUtil
import com.bella.mykpuapps.db.DataPemilih

class DataPemilihDiffCallback (
    private val oldDataPemilihList: List<DataPemilih>,
    private val newDataPemilihList: List<DataPemilih>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldDataPemilihList.size

    override fun getNewListSize(): Int = newDataPemilihList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldDataPemilihList[oldItemPosition].id == newDataPemilihList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldDataPemilihList[oldItemPosition]
        val newItem = newDataPemilihList[newItemPosition]
        return oldItem.nik == newItem.nik
                && oldItem.nama == newItem.nama
                && oldItem.nomorhp == newItem.nomorhp
                && oldItem.jeniskelamin == newItem.jeniskelamin
                && oldItem.date == newItem.date
                && oldItem.alamat == newItem.alamat
                && oldItem.latitude == newItem.latitude
                && oldItem.longitude == newItem.longitude
                && oldItem.gambar.contentEquals(newItem.gambar)
    }
}