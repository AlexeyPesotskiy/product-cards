package com.example.ideaplatformtest.data

import com.example.ideaplatformtest.data.local.ProductCardDao
import com.example.ideaplatformtest.data.local.ProductCardEntity
import com.example.ideaplatformtest.domain.ProductCardsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductCardsRepositoryImpl @Inject constructor(
    private val productCardDao: ProductCardDao,
) : ProductCardsRepository {
    override fun getAllProductsFlow(): Flow<List<ProductCardEntity>> =
        productCardDao.selectAllProducts()

    override suspend fun deleteProductCard(productCardId: Int) =
        productCardDao.deleteProductCard(productCardId)

    override suspend fun updateAmountInProductCard(newAmount: Int, productCardId: Int) =
        productCardDao.updateAmountInProductCard(
            newAmount = newAmount,
            productCardId = productCardId,
        )
}
