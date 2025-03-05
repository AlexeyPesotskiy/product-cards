package com.example.ideaplatformtest.di

import com.example.ideaplatformtest.data.ProductCardsRepositoryImpl
import com.example.ideaplatformtest.domain.ProductCardsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideProductCardsRepository(
        productCardsRepositoryImpl: ProductCardsRepositoryImpl,
    ): ProductCardsRepository
}