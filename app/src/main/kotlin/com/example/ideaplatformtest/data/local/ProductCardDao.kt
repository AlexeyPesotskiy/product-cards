package com.example.ideaplatformtest.data.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductCardDao {

    @Query("SELECT * FROM item")
    fun selectAllProducts(): Flow<List<ProductCardEntity>>

    @Query("DELETE FROM item WHERE id = :productCardId")
    suspend fun deleteProductCard(
        productCardId: Int,
    )

    @Query("UPDATE item SET amount = :newAmount WHERE id = :productCardId")
    suspend fun updateAmountInProductCard(
        newAmount: Int,
        productCardId: Int,
    )
}
