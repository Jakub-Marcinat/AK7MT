package com.example.myapplication.data.remoteData
import com.example.myapplication.data.remoteData.dto.CoinDetailDto
import com.example.myapplication.data.remoteData.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApi {

    @GET("v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinID}")
    suspend fun getCoinByID(@Path("coinID") coinID: String): CoinDetailDto
}