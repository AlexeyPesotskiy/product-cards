package com.example.ideaplatformtest.data

import com.example.ideaplatformtest.data.local.ProductCardEntity
import com.example.ideaplatformtest.domain.ProductCard
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class ProductCardMapper @Inject constructor() {

    fun mapToDomain(productCardEntity: ProductCardEntity): ProductCard = with(productCardEntity) {
        ProductCard(
            id = id,
            name = name,
            date = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date(time)),
            tags = tags.removeSurrounding("[", "]").split(",").map { tag ->
                tag.trim().removeSurrounding("\"")
            },
            amount = amount
        )
    }
}
