package com.example.myapplication.presentation.coinDetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import androidx.compose.runtime.State
import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.flow.launchIn
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.Constants
import com.example.myapplication.domain.useCases.getCoin.GetCoinUseCase
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle // Bundle with information about the saved state
): ViewModel() {

    private val _state = mutableStateOf(CoinDetailState()) //private to access only in view model
    val state: State<CoinDetailState> = _state //exposed immutable state to composables

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let {coinID ->
            getCoin(coinID)
        }
    }



    private fun getCoin(coinID: String) {
        getCoinUseCase(coinID).onEach { result -> when(result) {
            is Resource.Success -> {
                _state.value = CoinDetailState(coin = result.data)
            }
            is Resource.Error -> {
                _state.value = CoinDetailState(error = result.message ?: "Error")

            }
            is Resource.Loading -> {
                _state.value = CoinDetailState(isLoading = true)
            }
        } }.launchIn(viewModelScope)
    }
}