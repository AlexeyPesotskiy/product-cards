package com.example.ideaplatformtest.data

import com.example.ideaplatformtest.data.local.ProductCardDao
import com.example.ideaplatformtest.domain.ProductCard
import com.example.ideaplatformtest.domain.ProductCardsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductCardsRepositoryImpl @Inject constructor(
    private val productCardDao: ProductCardDao,
    private val productCardMapper: ProductCardMapper,
) : ProductCardsRepository {
    override fun getAllProductsFlow(): Flow<List<ProductCard>> =
        productCardDao.selectAllProducts().map { list ->
            list.map {
                productCardMapper.mapToDomain(it)
            }
        }

    override suspend fun deleteProductCard(productCardId: Int) =
        productCardDao.deleteProductCard(productCardId)

    override suspend fun updateAmountInProductCard(newAmount: Int, productCardId: Int) =
        productCardDao.updateAmountInProductCard(
            newAmount = newAmount,
            productCardId = productCardId,
        )
}
