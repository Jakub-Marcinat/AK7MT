package com.example.myapplication.domain.repositories

import com.example.myapplication.data.remoteData.dto.CoinDetailDto
import com.example.myapplication.data.remoteData.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinByID(coinID: String): CoinDetailDto
}