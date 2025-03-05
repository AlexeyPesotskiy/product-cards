package com.example.ideaplatformtest.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun ProductCardsSearchField(
    uiState: ProductCardsUiState,
    onAction: (ProductCardAction) -> Unit,
    focusManager: FocusManager,
) {
    OutlinedTextField(
        value = uiState.searchQuery,
        onValueChange = {
            onAction(ProductCardAction.OnUpdateSearchQuery(it))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "",
            )
        },
        trailingIcon = {
            if (uiState.searchQuery.isNotEmpty())
                IconButton(
                    onClick = { onAction(ProductCardAction.OnClearSearchQuery) },
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "",
                    )
                }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = { focusManager.clearFocus() },
        ),
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
    )
}
