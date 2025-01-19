package com.example.myapplication.presentation

sealed class Screen(val route: String) {
    object CoinListScreen: Screen("coinListScreen")
    object CoinDetailScreen: Screen("coinDetailScreen")
}