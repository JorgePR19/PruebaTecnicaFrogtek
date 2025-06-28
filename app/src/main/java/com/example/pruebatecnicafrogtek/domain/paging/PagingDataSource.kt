package com.example.pruebatecnicafrogtek.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pruebatecnicafrogtek.domain.models.BeersDomain
import com.example.pruebatecnicafrogtek.domain.repositories.ApiTask
import com.example.pruebatecnicafrogtek.utils.Constants
import javax.inject.Inject

class PagingDataSource @Inject constructor(private val apiTask: ApiTask) :
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
            val response = apiTask.fetchData(nextPageNumber)
            val data = response

            if (response.isNotEmpty())
                LoadResult.Page(
                    data = data,
                    prevKey = null,
                    nextKey = if (data.isNotEmpty()) nextPageNumber + 1 else null
                )
            else
                LoadResult.Error(Exception(Constants.ERROR_APPEND))

        } catch (e: Exception) {
            LoadResult.Error(Exception("Error desconocido: ${e.localizedMessage}"))
        }
    }
}