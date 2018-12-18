package com.example.mvvm.model

import com.squareup.moshi.Json
import java.io.Serializable

data class Person(

    @Json(name = "films")
    val films: List<String?>? = null,

    @Json(name = "homeworld")
    val homeworld: String? = null,

    @Json(name = "gender")
    val gender: String? = null,

    @Json(name = "skin_color")
    val skinColor: String? = null,

    @Json(name = "edited")
    val edited: String? = null,

    @Json(name = "created")
    val created: String? = null,

    @Json(name = "mass")
    val mass: String? = null,

    @Json(name = "vehicles")
    val vehicles: List<String?>? = null,

    @Json(name = "url")
    val url: String? = null,

    @Json(name = "hair_color")
    val hairColor: String? = null,

    @Json(name = "birth_year")
    val birthYear: String? = null,

    @Json(name = "eye_color")
    val eyeColor: String? = null,

    @Json(name = "species")
    val species: List<String?>? = null,

    @Json(name = "starships")
    val starships: List<String?>? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "height")
    val height: String? = null
) : Serializable