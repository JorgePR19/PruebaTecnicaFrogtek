package com.example.pruebatecnicafrogtek.domain.repositories

import com.example.pruebatecnicafrogtek.datasource.api.ApiContract
import com.example.pruebatecnicafrogtek.domain.mappers.ProductsDTOMapper
import com.example.pruebatecnicafrogtek.domain.models.BeersDomain
import com.example.pruebatecnicaliverpool.data.utilsresponse.ResponseStatus
import com.example.pruebatecnicaliverpool.data.utilsresponse.makeNetWorkCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface ApiTask {
    suspend fun fetchData(
        pageNumber: Int,
    ): ResponseStatus<List<BeersDomain>>
}

class ApiRepository @Inject constructor(private val apiContract: ApiContract) : ApiTask {

    override suspend fun fetchData(pageNumber: Int): ResponseStatus<List<BeersDomain>> {
        return withContext(Dispatchers.IO) {
            val productsDeferred = async { getProductsDeferred(pageNumber) }
            val productsResponse = productsDeferred.await()
            productsResponse
        }
    }

    private suspend  fun getProductsDeferred(pageNumber: Int): ResponseStatus<List<BeersDomain>> =
        makeNetWorkCall {
            val response = apiContract.getProducts(pageNumber)
            val mapper = ProductsDTOMapper()
            mapper.fromDtoToDomainList(response)
        }
}