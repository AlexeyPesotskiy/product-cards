package com.example.ideaplatformtest.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ideaplatformtest.R
import com.example.ideaplatformtest.ui.theme.IdeaPlatformTestTheme

@Composable
fun ProductCardsScreen() {
    val productCardsViewModel: ProductCardsViewModel = hiltViewModel()
    val uiState by productCardsViewModel.uiState.collectAsState()
    val onAction = { action: ProductCardAction ->
        productCardsViewModel.onAction(action)
    }
    Scaffold(
        topBar = { ProductTopBar() },
    ) { innerPadding ->
        ProductCardsSearchField()
        ProductCardsList(
            uiState = uiState,
            onAction = onAction,
            modifier = Modifier.padding(innerPadding)
        )
        ProductCardsDialogs(
            uiState = uiState,
            onAction = onAction,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductTopBar() {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(R.string.product_cards_screen_title)) },
    )
}

@Preview
@Composable
fun ProductTopBarPreview() {
    IdeaPlatformTestTheme {
        ProductTopBar()
    }
}