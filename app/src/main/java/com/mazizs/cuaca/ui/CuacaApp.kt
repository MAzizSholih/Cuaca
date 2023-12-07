@file:OptIn(ExperimentalMaterial3Api::class)
package com.mazizs.cuaca.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mazizs.cuaca.R
import com.mazizs.cuaca.ui.screens.HomeScreen
import com.mazizs.cuaca.ui.screens.CuacaViewModel

//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi CuacaApp
//yaitu untuk menyusun struktur utama aplikasi dan menetapkan komponen utama yang diperlukan
@Composable
fun CuacaApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold( //Umtuk menampilkan dan menyusun elemen elemen utama aplikasi
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { CuacaTopAppBar(scrollBehavior = scrollBehavior) }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            //Untuk menginisialisasi ViewModel
            val cuacaViewModel: CuacaViewModel = viewModel()
            //Untuk enampilkan layar utama atau HomeScreen dengan menggunakan data dari ViewModel
            HomeScreen(cuacaUiState = cuacaViewModel.cuacaUiState)
        }
    }
}

//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi CuacaTopAppBar yaitu untuk untuk menampilkan TopAppBar
@Composable
fun CuacaTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar( //Untuk menampilkan TopAppBar di tengah yang diatur dengan CenterAlignedTopAppBar
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.app_name), //Untuk menampilkan teks nama aplikasi
                style = MaterialTheme.typography.headlineSmall, //Untuk menggunakan gaya teks dari tema
            )
        },
        modifier = modifier
    )
}