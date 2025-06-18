package com.example.pruebatecnicafrogtek.domain.mappers

import com.example.pruebatecnicafrogtek.datasource.responses.BeerResponse
import com.example.pruebatecnicafrogtek.domain.models.BeersDomain
import com.example.pruebatecnicafrogtek.utils.Constants

class ProductsDTOMapper {

    fun fromDtoToDomain(beer: BeerResponse): BeersDomain {
        return BeersDomain(
            name = beer.name,
            tagline = beer.tagline,
            first_brewed = beer.first_brewed,
            ibu = beer.ibu ?: "NA",
            description = beer.description,
            productImage = Constants.IMAGE_ENDPOINT.plus(beer.image)
        )
    }

    fun fromDtoToDomainList(response: List<BeerResponse>): List<BeersDomain> {
        return response.map {
            fromDtoToDomain(it)
        }
    }
}