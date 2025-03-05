package com.example.ideaplatformtest.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ideaplatformtest.R
import com.example.ideaplatformtest.domain.ProductCard
import com.example.ideaplatformtest.ui.theme.IdeaPlatformTestTheme

@Composable
fun ProductCardsList(
    uiState: ProductCardsUiState,
    onAction: (ProductCardAction) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(300.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(uiState.productCardsList, key = { it.id }) { item ->
            ProductCardContent(
                item = item,
                onEditCardClick = {
                    onAction(ProductCardAction.OnOpenEditAmountDialog(item.amount, item.id))
                },
                onDeleteCardClick = {
                    onAction(ProductCardAction.OnOpenDeleteProductCardDialog(item.id))
                },
                modifier = Modifier
                    .animateItem()
                    .padding(bottom = 16.dp)
            )
        }
    }
}

@Composable
private fun ProductCardContent(
    item: ProductCard,
    onEditCardClick: () -> Unit,
    onDeleteCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(modifier) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            val weightModifier = Modifier.weight(1f)
            TitleAndButtonsRow(
                title = item.name,
                onEditCardClick = onEditCardClick,
                onDeleteCardClick = onDeleteCardClick,
                modifier = weightModifier,
            )
            TagsInChipsList(item.tags)
            InfoRow(
                amount = item.amount,
                date = item.date,
                modifier = weightModifier,
            )
        }
    }
}

@Composable
fun TitleAndButtonsRow(
    title: String,
    onEditCardClick: () -> Unit,
    onDeleteCardClick: () -> Unit,
    modifier: Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Left,
            modifier = modifier,
        )
        ButtonsBlock(
            onEditCardClick = onEditCardClick,
            onDeleteCardClick = onDeleteCardClick
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsInChipsList(
    tagsList: List<String>,
) {
    if (tagsList[0] != "")
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy((-12).dp)
        ) {
            tagsList.forEach {
                SuggestionChip(
                    onClick = {},
                    label = {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.labelMedium
                        )
                    },
                )
            }
        }
}

@Composable
private fun InfoRow(
    amount: Int,
    date: String,
    modifier: Modifier,
) {
    Row {
        InfoWithTitle(
            title = stringResource(R.string.product_cards_screen_amount_info_title),
            info = amount.toString(),
            modifier = modifier,
        )
        InfoWithTitle(
            title = stringResource(R.string.product_cards_screen_date_added_info_title),
            info = date,
            modifier = modifier,
        )
    }
}

@Composable
private fun ButtonsBlock(
    onEditCardClick: () -> Unit,
    onDeleteCardClick: () -> Unit,
) {
    IconButton(onEditCardClick) {
        Icon(
            Icons.Default.Edit,
            "",
            tint = Color.Magenta,
        )
    }
    IconButton(onDeleteCardClick) {
        Icon(
            Icons.Default.Delete,
            "",
            tint = Color.Red
        )
    }
}

@Composable
private fun InfoWithTitle(
    modifier: Modifier = Modifier,
    title: String,
    info: String,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier,
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.SemiBold,
        )
        Text(info)
    }
}

@Preview
@Composable
fun ProductCardContentPreview() {
    IdeaPlatformTestTheme {
        ProductCardContent(
            item = ProductCard(
                id = 1,
                name = "iPhone 13 iPhone 13 iPhone 13 iPhone 13",
                date = "05.04.2020",
                tags = listOf(
                    "new",
                    "good choice",
                    "good choice",
                    "new",
                    "good choice",
                ),
                amount = 15
            ),
            onEditCardClick = {},
            onDeleteCardClick = {},
        )
    }
}
