package com.example.ideaplatformtest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductCardEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productCardDao(): ProductCardDao
}
