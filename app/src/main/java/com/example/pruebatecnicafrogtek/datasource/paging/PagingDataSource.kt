package com.example.pruebatecnicafrogtek.datasource.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pruebatecnicafrogtek.domain.models.BeersDomain
import com.example.pruebatecnicafrogtek.domain.usecases.PagingUseCase

class PagingDataSource(val useCase: PagingUseCase) :
    PagingSource<Int, BeersDomain>() {
    override fun getRefreshKey(state: PagingState<Int, BeersDomain>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BeersDomain> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = useCase(nextPageNumber)
            val data = response.data ?: emptyList()

            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = if (data.isNotEmpty()) nextPageNumber + 1 else null
            )

        } catch (e: Exception) {
            LoadResult.Error(
                Exception("Error desconocido: ${e.localizedMessage}")
            )
        }
    }
}