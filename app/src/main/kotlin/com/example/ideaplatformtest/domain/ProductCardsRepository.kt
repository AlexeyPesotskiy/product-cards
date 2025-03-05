package com.example.ideaplatformtest.domain

import kotlinx.coroutines.flow.Flow

interface ProductCardsRepository {
    fun getAllProductsFlow(): Flow<List<ProductCard>>

    suspend fun deleteProductCard(productCardId: Int)

    suspend fun updateAmountInProductCard(newAmount: Int, productCardId: Int)
}
