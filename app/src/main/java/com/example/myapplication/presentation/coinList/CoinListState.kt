package com.example.myapplication.presentation.coinList

import com.example.myapplication.domain.models.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)