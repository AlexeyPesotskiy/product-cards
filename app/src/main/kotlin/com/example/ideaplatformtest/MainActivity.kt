package com.example.ideaplatformtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.ideaplatformtest.ui.ProductCardsApp
import com.example.ideaplatformtest.ui.theme.IdeaPlatformTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IdeaPlatformTestTheme {
                ProductCardsApp()
            }
        }
    }
}
