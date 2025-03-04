package com.example.ideaplatformtest.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class ProductCardEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val time: Int,
    val tags: String,
    val amount: Int,
)
