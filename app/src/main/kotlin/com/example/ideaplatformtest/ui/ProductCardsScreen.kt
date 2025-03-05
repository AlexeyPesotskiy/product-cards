package com.example.ideaplatformtest.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ideaplatformtest.R

@Composable
fun ProductCardsScreen() {
    val productCardsViewModel: ProductCardsViewModel = hiltViewModel()
    val onDeleteCard = { it: Int ->
        productCardsViewModel.deleteProduct(it)
    }
    val onOpenEditCardDialog = { it: Int ->
        productCardsViewModel.openEditProductAmountDialog(it)
    }
    val uiState by productCardsViewModel.uiState.collectAsState()
    Scaffold(
        topBar = { ProductTopBar() },
    ) { innerPadding ->
        ProductCardsSearchField()
        ProductCardsList(
            uiState = uiState,
            onDeleteCard = onDeleteCard,
            onOpenEditCardDialog = onOpenEditCardDialog,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductTopBar() {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(R.string.product_cards_screen_title)) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors().copy(
            containerColor = Color(0xFF91DAF5)
        )
    )
}
