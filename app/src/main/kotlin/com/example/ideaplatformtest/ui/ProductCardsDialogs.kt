package com.example.ideaplatformtest.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ideaplatformtest.R
import com.example.ideaplatformtest.ui.theme.IdeaPlatformTestTheme

@Composable
fun ProductCardsDialogs(
    uiState: ProductCardsUiState,
    onAction: (ProductCardAction) -> Unit,
) {
    if (uiState.showEditDialog) {
        EditProductAmountDialog(
            productAmountInDialog = uiState.productAmountInEditDialog,
            onAction = onAction,
        )
    } else if (uiState.showDeleteDialog) {
        DeleteCardDialog(
            onAction = onAction,
        )
    }
}

@Composable
private fun EditProductAmountDialog(
    productAmountInDialog: Int,
    onAction: (ProductCardAction) -> Unit,
) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = ""
            )
        },
        title = { Text(stringResource(R.string.edit_product_amount_dialog_title)) },
        text = {
            EditProductAmountDialogText(
                productAmountInDialog = productAmountInDialog,
                onAction = onAction,
            )
        },
        onDismissRequest = { onAction(ProductCardAction.OnCloseEditAmountDialog) },
        confirmButton = {
            TextButton(
                onClick = { onAction(ProductCardAction.OnSaveNewProductAmount) },
                content = {
                    Text(stringResource(R.string.edit_product_amount_dialog_confirm_button))
                }
            )
        },
        dismissButton = {
            TextButton(
                onClick = { onAction(ProductCardAction.OnCloseEditAmountDialog) },
                content = {
                    Text(stringResource(R.string.edit_product_amount_dialog_dismiss_button))
                },
            )
        },
    )
}

@Composable
private fun EditProductAmountDialogText(
    productAmountInDialog: Int,
    onAction: (ProductCardAction) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(
            onClick = { onAction(ProductCardAction.OnDecreaseProductAmount) }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_decrease_circle),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
            )
        }
        Text(
            text = productAmountInDialog.toString(),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        IconButton(
            onClick = { onAction(ProductCardAction.OnIncreaseProductAmount) }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_increase_circle),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}

@Composable
private fun DeleteCardDialog(
    onAction: (ProductCardAction) -> Unit,
) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = ""
            )
        },
        title = { Text(stringResource(R.string.delete_product_card_dialog_title)) },
        text = { Text(stringResource(R.string.delete_product_card_dialog_text)) },
        onDismissRequest = { onAction(ProductCardAction.OnCloseDeleteProductCardDialog) },
        confirmButton = {
            TextButton(
                onClick = { onAction(ProductCardAction.OnDeleteProductCard) },
                content = { Text(stringResource(R.string.delete_product_card_dialog_confirm_button)) }
            )
        },
        dismissButton = {
            TextButton(
                onClick = { onAction(ProductCardAction.OnCloseDeleteProductCardDialog) },
                content = { Text(stringResource(R.string.delete_product_card_dialog_dismiss_button)) },
            )
        },
    )
}

@Preview
@Composable
fun DeleteCardDialogPreview() {
    IdeaPlatformTestTheme {
        DeleteCardDialog(
            onAction = {},
        )
    }
}

@Preview
@Composable
fun EditProductAmountDialogPreview() {
    IdeaPlatformTestTheme {
        EditProductAmountDialog(
            productAmountInDialog = 25,
            onAction = {},
        )
    }
}
