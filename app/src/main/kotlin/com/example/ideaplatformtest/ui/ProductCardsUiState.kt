package com.example.ideaplatformtest.ui

import androidx.compose.runtime.Immutable
import com.example.ideaplatformtest.domain.ProductCard

@Immutable
data class ProductCardsUiState(
    val searchQuery: String = "",
    val productCardsList: List<ProductCard> = emptyList(),
    val dialogCardId: Int = 0,
    val showEditDialog: Boolean = false,
    val productAmountInEditDialog: Int = 0,
    val showDeleteDialog: Boolean = false,
)
