package com.example.pruebatecnicafrogtek.datasource.api

import com.example.pruebatecnicafrogtek.datasource.responses.BeerResponse
import com.example.pruebatecnicafrogtek.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiContract {
    @GET(Constants.BEERS_ENDPOINT)
    suspend fun getProducts(
        @Query("page") pageNumber: Int,
    ): List<BeerResponse>
}
