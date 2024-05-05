package com.bella.mykpuapps.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bella.mykpuapps.databinding.ListItemDataBinding
import com.bella.mykpuapps.db.DataPemilih
import com.bella.mykpuapps.entry.FormEntryActivity
import com.bella.mykpuapps.helper.DataPemilihDiffCallback
import java.util.ArrayList

class InfoPemilihAdapter : RecyclerView.Adapter<InfoPemilihAdapter.DataPemilihViewHolder>() {
    private val listDataPemilih = ArrayList<DataPemilih>()

    fun setListDataPemilih(listDataPemilih: List<DataPemilih>) {
        val diffCallback = DataPemilihDiffCallback(this.listDataPemilih, listDataPemilih)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listDataPemilih.clear()
        this.listDataPemilih.addAll(listDataPemilih)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataPemilihViewHolder {
        val binding = ListItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataPemilihViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataPemilihViewHolder, position: Int) {
        holder.bind(listDataPemilih[position])
    }

    override fun getItemCount(): Int {
        return listDataPemilih.size
    }

    inner class DataPemilihViewHolder(private val binding: ListItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(datapemilih: DataPemilih) {
            with(binding) {

                tvNik.text = datapemilih.nik?.toString() ?: ""
                tvItemDate.text = datapemilih.date
                tvNama.text = datapemilih.nama

                cvDatapemilih.setOnClickListener {
                    val intent = Intent(it.context, FormEntryActivity::class.java)
                    intent.putExtra(FormEntryActivity.EXTRA_NOTE, datapemilih)
                    it.context.startActivity(intent)
                }
            }
        }
    }
}