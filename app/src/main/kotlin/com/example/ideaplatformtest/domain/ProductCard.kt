package com.example.ideaplatformtest.domain

data class ProductCard(
    val name: String,
    val time: String,
    val tags: List<String> = emptyList(),
    val amount: Int,
)
