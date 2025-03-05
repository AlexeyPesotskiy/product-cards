package com.example.ideaplatformtest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ideaplatformtest.domain.ProductCardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductCardsViewModel @Inject constructor(
    private val repository: ProductCardsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductCardsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        observeProductCardsFlow()
    }

    fun updateSearchQuery(newSearchQuery: String) = _uiState.update {
        it.copy(
            searchQuery = newSearchQuery,
        )
    }

    fun openEditProductAmountDialog(currentAmount: Int) = _uiState.update {
        it.copy(
            productAmountInDialog = currentAmount,
        )
    }

    fun editProductAmountInDialog(newAmount: Int) = _uiState.update {
        it.copy(
            productAmountInDialog = newAmount,
        )
    }

    fun saveNewAmountOfProduct(newAmount: Int, productId: Int) = viewModelScope.launch {
        repository.updateAmountInProductCard(
            newAmount = newAmount,
            productCardId = productId,
        )
    }

    fun deleteProduct(productId: Int) {
        viewModelScope.launch {
            repository.deleteProductCard(productId)
        }
    }

    private fun observeProductCardsFlow() {
        viewModelScope.launch {
            repository.getAllProductsFlow().collect { list ->
                _uiState.update { state ->
                    state.copy(
                        productCardsList = list
                    )
                }
            }
        }
    }
}
