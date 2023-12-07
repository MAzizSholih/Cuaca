package com.mazizs.cuaca.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mazizs.cuaca.network.OpenWeatherMapApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

//Untuk mendefinisikan status tampilan cuaca
sealed interface CuacaUiState {
    //Saat keadaan berhasil, maaka menyertakan informasi cuaca sebagai data
    data class Success(val weatherInfo: String) : CuacaUiState
    object Error : CuacaUiState //Saat Error, maka menunjukkan kegagalan dalam memuat data cuaca
    object Loading : CuacaUiState //Saat Loading, maka menunjukkan bahwa aplikasi sedang dalam proses memuat data

}
//Merupakan kelas ViewModel untuk mengelola data cuaca
class CuacaViewModel : ViewModel() {
    //Mutable state yang merepresentasikan keadaan UI cuaca
    var cuacaUiState: CuacaUiState by mutableStateOf(CuacaUiState.Loading)
        private set
    init { //Merupakan blok inisialisasi untuk mendapatkan data cuaca default yaitu kota Jakarta
        val defaultCity = "Yogyakarta"
        getWeatherData(defaultCity)
    }
    //Untuk mendapatkan data cuaca dari API
    private fun getWeatherData(city: String) {
        //Untuk melakukan pemanggilan API secara asinkron
        viewModelScope.launch {
            cuacaUiState = CuacaUiState.Loading //Saat memulai pengambilan data, ubah status menjadi Loading
            cuacaUiState = try { //untuk menangani kesalahan yang mungkin terjadi saat pemanggilan API
                //Untuk memanggil API OpenWeatherMap untuk mendapatkan data cuaca
                val weatherResult = OpenWeatherMapApi.retrofitService.getWeather(city)
                CuacaUiState.Success(
                    "Suhu di $city: ${weatherResult.main.temp}°C"
                )
            } catch (e: IOException) {
                CuacaUiState.Error
            } catch (e: HttpException) {
                CuacaUiState.Error
            }
        }
    }
}


