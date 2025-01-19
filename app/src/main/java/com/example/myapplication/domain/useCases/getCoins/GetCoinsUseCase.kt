package com.example.myapplication.domain.useCases.getCoins

import com.example.myapplication.common.Resource
import com.example.myapplication.data.remoteData.dto.toCoin
import com.example.myapplication.domain.models.Coin
import com.example.myapplication.domain.repositories.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(coins)) //forwarding data to view model
        }catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "Niekde nastala chyba :} "))
        }catch (e: IOException) {
            emit(Resource.Error<List<Coin>>("Skontroluj internety"))
        }
    }

}