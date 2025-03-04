package com.example.ideaplatformtest.domain

import com.example.ideaplatformtest.data.local.ProductCardEntity
import kotlinx.coroutines.flow.Flow

interface ProductCardsRepository {
    fun getAllProductsFlow(): Flow<List<ProductCardEntity>>

    suspend fun deleteProductCard(productCardId: Int)

    suspend fun updateAmountInProductCard(newAmount: Int, productCardId: Int)
}
