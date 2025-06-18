package com.example.pruebatecnicafrogtek.datasource.responses

import com.google.gson.annotations.SerializedName


data class BeerResponse(
    @SerializedName("name") val name: String,
    @SerializedName("tagline") val tagline: String,
    @SerializedName("first_brewed") val first_brewed: String,
    @SerializedName("ibu") val ibu: String? = "",
    @SerializedName("description") val description: String,
    @SerializedName("image") val image: String,
)
