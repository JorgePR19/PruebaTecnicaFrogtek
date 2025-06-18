package com.example.pruebatecnicafrogtek.domain.usecases

import com.example.pruebatecnicafrogtek.domain.models.BeersDomain
import com.example.pruebatecnicafrogtek.domain.repositories.ApiRepository
import com.example.pruebatecnicaliverpool.data.utilsresponse.ResponseStatus
import javax.inject.Inject

class PagingUseCase @Inject constructor(private val repository: ApiRepository) {
    suspend operator fun invoke(
        page: Int
    ): ResponseStatus<List<BeersDomain>> {
        return repository.fetchData(page)
    }
}
