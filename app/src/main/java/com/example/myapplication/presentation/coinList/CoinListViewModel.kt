package com.example.myapplication.presentation.coinList

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.common.Resource
import com.example.myapplication.domain.useCases.getCoins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.launchIn
import androidx.lifecycle.viewModelScope
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
): ViewModel() {

    private val _state = mutableStateOf(CoinListState()) //private to access only in view model
    val state: State<CoinListState> = _state //exposed immutable state to composables

    init {
        getCoins()
    }



    private fun getCoins() {
        getCoinsUseCase().onEach { result -> when(result) {
            is Resource.Success -> {
                _state.value = CoinListState(coins = result.data ?: emptyList())
            }
            is Resource.Error -> {
                _state.value = CoinListState(error = result.message ?: "Error")

            }
            is Resource.Loading -> {
                _state.value = CoinListState(isLoading = true)
            }
        } }.launchIn(viewModelScope)
    }
}