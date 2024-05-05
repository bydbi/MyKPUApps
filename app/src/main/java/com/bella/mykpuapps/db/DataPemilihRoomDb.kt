package com.bella.mykpuapps.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataPemilih::class], version = 1)
abstract class DataPemilihRoomDb : RoomDatabase(){
    abstract fun datapemilihDao(): DataPemilihDao

    companion object {
        @Volatile
        private var INSTANCE: DataPemilihRoomDb? = null

        @JvmStatic
        fun getDatabase(context: Context): DataPemilihRoomDb {
            if (INSTANCE == null) {
                synchronized(DataPemilihRoomDb::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DataPemilihRoomDb::class.java, "datapemilih_database"
                    )
                        .build()
                }
            }
            return INSTANCE as DataPemilihRoomDb
        }
    }
}