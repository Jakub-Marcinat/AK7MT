package com.example.myapplication.di

import com.example.myapplication.common.Constants
import com.example.myapplication.data.remoteData.CoinApi
import com.example.myapplication.data.repositories.CoinRepositoryImplementation
import com.example.myapplication.domain.repositories.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class) //lifetime of the depencies in the module
object AppModule {

    @Provides
    @Singleton //single instance
    fun provideApi(): CoinApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(CoinApi::class.java)
    }


    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinApi): CoinRepository {
        return CoinRepositoryImplementation(api)
    }
}