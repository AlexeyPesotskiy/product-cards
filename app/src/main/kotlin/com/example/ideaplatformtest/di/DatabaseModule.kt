package com.example.ideaplatformtest.di

import android.content.Context
import androidx.room.Room
import com.example.ideaplatformtest.data.local.AppDatabase
import com.example.ideaplatformtest.data.local.ProductCardDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext
        context: Context,
    ): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "products_database")
            .createFromAsset("data.db")
            .build()

    @Provides
    fun provideProductCardDao(
        database: AppDatabase,
    ): ProductCardDao = database.productCardDao()
}