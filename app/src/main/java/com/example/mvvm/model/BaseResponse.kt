package com.example.mvvm.model

import com.squareup.moshi.Json

data class BaseResponse(

    @Json(name = "next")
    val next: String? = null,

    @Json(name = "previous")
    val previous: Any? = null,

    @Json(name = "count")
    val count: Int? = null,

    @Json(name = "results")
    val results: List<Person>? = null
)