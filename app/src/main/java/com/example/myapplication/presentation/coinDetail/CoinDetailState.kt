package com.example.myapplication.presentation.coinDetail

import com.example.myapplication.domain.models.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)