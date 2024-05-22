package com.dmribeiro87.kaizenapp.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dmribeiro87.kaizenapp.core.data.local.entity.EventEntity


@Database(entities = [EventEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}
