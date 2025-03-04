package com.example.ideaplatformtest.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ProductCardsApp() {
    Scaffold(
        topBar = { ProductTopBar() }
    ) { innerPadding ->
        ProductCardsSearchField()
        ProductCardsList(Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductTopBar() {
    CenterAlignedTopAppBar(
        title = { Text("Список товаров") },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors().copy(
            containerColor = Color(0xFF91DAF5)
        )
    )
}
