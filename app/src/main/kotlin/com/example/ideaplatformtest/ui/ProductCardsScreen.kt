package com.example.ideaplatformtest.ui

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ideaplatformtest.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCardsScreen() {
    val productCardsViewModel: ProductCardsViewModel = hiltViewModel()
    val uiState by productCardsViewModel.uiState.collectAsState()
    val onAction = { action: ProductCardAction ->
        productCardsViewModel.onAction(action)
    }
    val focusManager = LocalFocusManager.current
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = { ProductTopBar(scrollBehavior) },
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .pointerInput(Unit) {
                detectTapGestures(onTap = { focusManager.clearFocus() })
            },
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            ProductCardsSearchField(
                uiState = uiState,
                onAction = onAction,
                focusManager
            )
            ProductCardsList(
                uiState = uiState,
                onAction = onAction,
            )
        }
        ProductCardsDialogs(
            uiState = uiState,
            onAction = onAction,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(R.string.product_cards_screen_title)) },
        scrollBehavior = scrollBehavior,
    )
}
