package com.example.mvvm.remote

import com.example.mvvm.model.BaseResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarsApi {
    @GET("people/")
    fun getPerson(@Query("page") page: Int): Deferred<BaseResponse>
}

