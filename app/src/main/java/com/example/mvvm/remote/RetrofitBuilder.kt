package com.example.mvvm.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

abstract class RetrofitBuilder {
    companion object {
        fun buildRetrofit(): StarWarsApi = Retrofit.Builder()
            .baseUrl("https://swapi.co/api/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(StarWarsApi::class.java)
    }
}