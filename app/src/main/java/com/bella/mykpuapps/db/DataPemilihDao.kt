package com.bella.mykpuapps.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DataPemilihDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(datapemilih: DataPemilih)

    @Update
    fun update(datapemilih: DataPemilih)

    @Delete
    fun delete(datapemilih: DataPemilih)

    @Query("SELECT * from datapemilih ORDER BY id ASC")
    fun getAllDataPemilih(): LiveData<List<DataPemilih>>

    @Query("SELECT * FROM datapemilih WHERE nik = :nik")
    fun getDataPemilihByNIK(nik: String): LiveData<DataPemilih>
}