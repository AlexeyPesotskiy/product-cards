package com.example.ideaplatformtest.domain

data class ProductCard(
    val id: Int,
    val name: String,
    val tags: List<String> = emptyList(),
    val amount: Int,
    val date: String,
)
