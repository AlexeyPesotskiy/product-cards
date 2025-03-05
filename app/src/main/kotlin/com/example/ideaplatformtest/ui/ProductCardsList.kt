package com.example.ideaplatformtest.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.sp
import com.example.ideaplatformtest.R
import com.example.ideaplatformtest.domain.ProductCard
import com.example.ideaplatformtest.ui.theme.IdeaPlatformTestTheme

@Composable
fun ProductCardsList(
    uiState: ProductCardsUiState,
    onOpenEditCardDialog: (Int) -> Unit,
    onDeleteCard: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(uiState.productCardsList, key = { it.id }) { item ->
            ProductCardContent(
                item = item,
                onEditCardClick = {
                    onOpenEditCardDialog(item.amount)
                },
                onDeleteCardClick = {
                    onDeleteCard(item.id)
                },
                modifier = Modifier.animateItem()
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = item.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Left,
                    modifier = weightModifier,
                )
                ButtonsBlock(
                    onEditCardClick = onEditCardClick,
                    onDeleteCardClick = onDeleteCardClick
                )
            }
            TagsInChipsList(item.tags)
            Row {
                InfoWithTitle(
                    title = stringResource(R.string.product_cards_screen_amount_info_title),
                    info = item.amount.toString(),
                    modifier = weightModifier,
                )
                InfoWithTitle(
                    title = stringResource(R.string.product_cards_screen_date_added_info_title),
                    info = item.time,
                    modifier = weightModifier,
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsInChipsList(
    tagsList: List<String>,
) {
    if (tagsList[0] != "")
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy((-8).dp)
        ) {
            tagsList.forEach {
                SuggestionChip(
                    onClick = {},
                    label = {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.labelLarge
                        )
                    },
                )
            }
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
                time = "05.04.2020",
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
