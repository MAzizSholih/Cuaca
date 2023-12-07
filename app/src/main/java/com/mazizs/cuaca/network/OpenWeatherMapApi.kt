package com.mazizs.cuaca.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

//Merupakan interface yang mendefinisikan layanan untuk pemanggilan API OpenWeatherMap
interface OpenWeatherMapService {
    @GET("weather") //Unntuk mendapatkan data cuaca berdasarkan nama kota
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String = "9960914c2d980e982dd402ca77c6089d"
    ): CuacaResponse
}

//Merupaqkan objek singleton yang menyediakan instance layanan Retrofit untuk API OpenWeatherMap
object OpenWeatherMapApi {
    //Base URL untuk API OpenWeatherMap
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: OpenWeatherMapService by lazy {
        retrofit.create(OpenWeatherMapService::class.java)
    }
}

//Merupakan data class yang merepresentasikan respon data cuaca dari API OpenWeatherMap
data class CuacaResponse(val main: Main)
//Merupakan data class yang merepresentasikan bagian utama dari respon data cuaca
data class Main(val temp: Double)