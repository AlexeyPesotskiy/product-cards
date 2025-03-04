package com.example.ideaplatformtest.ui

import androidx.lifecycle.ViewModel
import com.example.ideaplatformtest.domain.ProductCardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductCardsViewModel @Inject constructor(
    private val repository: ProductCardsRepository,
) : ViewModel() {
}
