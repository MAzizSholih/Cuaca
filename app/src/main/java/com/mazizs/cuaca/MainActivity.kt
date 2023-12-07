package com.mazizs.cuaca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.mazizs.cuaca.ui.CuacaApp
import com.mazizs.cuaca.ui.theme.CuacaTheme

//Fungsi onCreate ini digunakan untuk mengedit tampilan aktivitas utama dengan menggunakan komponen UI yang telah didefinisikan dalam CuacaApp
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            CuacaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CuacaApp()
                }
            }
        }
    }
}