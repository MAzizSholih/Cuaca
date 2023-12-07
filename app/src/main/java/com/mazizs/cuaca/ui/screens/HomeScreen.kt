package com.mazizs.cuaca.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mazizs.cuaca.R
import com.mazizs.cuaca.ui.theme.CuacaTheme

//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi HomeScreen yaitu untuk menampilkan HomeScreen status cuaca
@Composable
fun HomeScreen(
    cuacaUiState: CuacaUiState, modifier: Modifier = Modifier
) {
    when (cuacaUiState) {
        //Jika sedang memuat atau Loading, maka tampilkan LoadingScreen
        is CuacaUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        //Jika setelah loading berhasil, maka tampilkan ResultScreen dengan data cuaca yang berhasil diambil
        is CuacaUiState.Success -> ResultScreen(
            cuacaUiState.weatherInfo, modifier = modifier.fillMaxWidth()
        )
        //Jika eror, maka tampilkan ErrorScreen
        is CuacaUiState.Error -> ErrorScreen( modifier = modifier.fillMaxSize())
    }
}

//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi LoadingScreen yaitu untuk menampilkan LoadingScreen atau tampilan saat loading
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image( //Untuk menampilkan gambar loading
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.memuat)
    )
}

//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi ErrorScreen yaitu untuk menampilkan ErrorScreen atau tampilan saat eror
@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column( //Untuk menata elemen-elemen UI
        modifier = modifier,
        verticalArrangement = Arrangement.Center, //Untuk menyusun elemen secara vertikal di tengah
        horizontalAlignment = Alignment.CenterHorizontally //untuk menyusun elemen secara horizontal di tengah
    ) {
        Image( //Untuk menampilkan gambar koneksi eror
            painter = painterResource(id = R.drawable.connection_error), contentDescription = ""
        )
        //Untuk menampilkan teks informasi gagal memuat
        Text(text = stringResource(R.string.gagal_memuat), modifier = Modifier.padding(16.dp))
    }
}

//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi ResultScreen yaitu untuk menampilkan informasi cuaca
@Composable
fun ResultScreen(weatherInfo: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = weatherInfo)
    }
}

//Fungsi di bawah ini adalah komponen Composable yang digunakan untuk menampilkan preview atau pratinjau tampilan saat sedang memuat atau loading
@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    CuacaTheme {
        LoadingScreen()
    }
}

//Fungsi di bawah ini adalah komponen Composable yang digunakan untuk menampilkan preview atau pratinjau tampilan saat eror
@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    CuacaTheme {
        ErrorScreen()
    }
}

//Fungsi di bawah ini adalah komponen Composable yang digunakan untuk menampilkan preview atau pratinjau tampilan hasil informasi cuaca
@Preview(showBackground = true)
@Composable
fun PhotosGridScreenPreview() {
    CuacaTheme {
        ResultScreen(stringResource(R.string.placeholder_success))
    }
}