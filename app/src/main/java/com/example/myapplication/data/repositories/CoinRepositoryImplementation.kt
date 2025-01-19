package com.example.myapplication.data.repositories

import com.example.myapplication.data.remoteData.CoinApi
import com.example.myapplication.data.remoteData.dto.CoinDetailDto
import com.example.myapplication.data.remoteData.dto.CoinDto
import com.example.myapplication.domain.repositories.CoinRepository
import javax.inject.Inject

class CoinRepositoryImplementation @Inject constructor(private val api: CoinApi):CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinByID(coinID: String): CoinDetailDto {

        return api.getCoinByID(coinID)
    }
}