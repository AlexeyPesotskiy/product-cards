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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.ideaplatformtest.R

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
        label = {
            Text(stringResource(R.string.product_cards_search_field_label))
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
            .padding(
                top = 8.dp,
                bottom = 16.dp,
            ),
    )
}
