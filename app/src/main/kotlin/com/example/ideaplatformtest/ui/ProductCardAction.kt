package com.example.ideaplatformtest.ui

sealed interface ProductCardAction {
    data class OnUpdateSearchQuery(val newSearchQuery: String) : ProductCardAction
    data object OnClearSearchQuery : ProductCardAction

    data class OnOpenEditAmountDialog(val currentAmount: Int, val productId: Int) :
        ProductCardAction

    data object OnIncreaseProductAmount : ProductCardAction
    data object OnDecreaseProductAmount : ProductCardAction
    data object OnSaveNewProductAmount : ProductCardAction
    data object OnCloseEditAmountDialog : ProductCardAction

    data class OnOpenDeleteProductCardDialog(val productId: Int) : ProductCardAction
    data object OnDeleteProductCard : ProductCardAction
    data object OnCloseDeleteProductCardDialog : ProductCardAction
}
