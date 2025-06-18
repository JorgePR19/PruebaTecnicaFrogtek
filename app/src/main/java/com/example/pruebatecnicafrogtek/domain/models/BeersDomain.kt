package com.example.pruebatecnicafrogtek.domain.models

import com.example.pruebatecnicafrogtek.utils.Constants

data class BeersDomain(
    val name: String,
    val tagline: String,
    val first_brewed: String,
    val ibu: String,
    val description: String,
    var productImage: String = Constants.IMAGE_ENDPOINT
)
