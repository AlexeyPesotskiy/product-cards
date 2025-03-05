package com.example.ideaplatformtest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ideaplatformtest.domain.ProductCard
import com.example.ideaplatformtest.domain.ProductCardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductCardsViewModel @Inject constructor(
    private val repository: ProductCardsRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductCardsUiState())
    val uiState = _uiState.asStateFlow()

    private var latestCardList: List<ProductCard> = emptyList()

    init {
        observeProductCardsFlow()
    }

    fun onAction(action: ProductCardAction) {
        when (action) {
            is ProductCardAction.OnUpdateSearchQuery -> {
                updateSearchQuery(action.newSearchQuery)
            }
            ProductCardAction.OnClearSearchQuery -> clearSearchQuery()

            is ProductCardAction.OnOpenEditAmountDialog -> openEditProductAmountDialog(
                currentAmount = action.currentAmount,
                productId = action.productId
            )
            ProductCardAction.OnIncreaseProductAmount -> incrementProductAmountInDialog()
            ProductCardAction.OnDecreaseProductAmount -> decrementProductAmountInDialog()
            ProductCardAction.OnSaveNewProductAmount -> saveNewAmountOfProduct()
            ProductCardAction.OnCloseEditAmountDialog -> closeEditProductAmountDialog()

            is ProductCardAction.OnOpenDeleteProductCardDialog -> {
                openDeleteProductDialog(action.productId)
            }
            ProductCardAction.OnDeleteProductCard -> deleteCurrentProduct()
            ProductCardAction.OnCloseDeleteProductCardDialog -> closeDeleteProductDialog()
        }
    }

    private fun updateSearchQuery(newSearchQuery: String) = _uiState.update {
        it.copy(
            productCardsList = latestCardList.filterBySearchQuery(newSearchQuery),
            searchQuery = newSearchQuery,
        )
    }

    private fun clearSearchQuery() = updateSearchQuery("")

    private fun openEditProductAmountDialog(currentAmount: Int, productId: Int) = _uiState.update {
        it.copy(
            productAmountInEditDialog = currentAmount,
            dialogCardId = productId,
            showEditDialog = true,
        )
    }

    private fun incrementProductAmountInDialog() = _uiState.update {
        it.copy(
            productAmountInEditDialog = uiState.value.productAmountInEditDialog + 1,
        )
    }

    private fun decrementProductAmountInDialog() =
        uiState.value.productAmountInEditDialog.let { amount ->
            if (amount != 0)
                _uiState.update {
                    it.copy(
                        productAmountInEditDialog = amount - 1,
                    )
                }
        }

    private fun closeEditProductAmountDialog() = _uiState.update {
        it.copy(
            productAmountInEditDialog = 0,
            dialogCardId = 0,
            showEditDialog = false,
        )
    }

    private fun saveNewAmountOfProduct() = viewModelScope.launch {
        repository.updateAmountInProductCard(
            newAmount = uiState.value.productAmountInEditDialog,
            productCardId = uiState.value.dialogCardId,
        )
        closeEditProductAmountDialog()
    }

    private fun openDeleteProductDialog(productId: Int) = _uiState.update {
        it.copy(
            showDeleteDialog = true,
            dialogCardId = productId,
        )
    }

    private fun closeDeleteProductDialog() = _uiState.update {
        it.copy(
            showDeleteDialog = false,
            dialogCardId = 0,
        )
    }

    private fun deleteCurrentProduct() {
        viewModelScope.launch {
            repository.deleteProductCard(uiState.value.dialogCardId)
        }
        closeDeleteProductDialog()
    }

    private fun observeProductCardsFlow() {
        viewModelScope.launch {
            repository.getAllProductsFlow().collect { list ->
                latestCardList = list
                _uiState.update { state ->
                    state.copy(
                        productCardsList = list.filterBySearchQuery(uiState.value.searchQuery),
                    )
                }
            }
        }
    }

    private fun List<ProductCard>.filterBySearchQuery(searchQuery: String) = filter {
        it.name.uppercase().contains(searchQuery.uppercase())
    }
}
