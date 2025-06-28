package com.example.pruebatecnicafrogtek.ui.views

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.LoadState.NotLoading
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.compose.LazyPagingItems
import com.example.pruebatecnicafrogtek.domain.models.BeersDomain
import com.example.pruebatecnicafrogtek.domain.paging.PagingDataSource
import com.example.pruebatecnicafrogtek.utils.Constants
import com.example.pruebatecnicafrogtek.utils.Constants.getTypeOfError
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class UiState {
    data class LoadingData(val repeat: Int) : UiState()
    data class ShowMessage(val message: String) : UiState()
}


@HiltViewModel
class MainViewModel @Inject constructor(
    private val pagingDataSource: PagingDataSource
) : ViewModel() {

    val beersPaging = Pager(PagingConfig(pageSize = 30)) {
        pagingDataSource
    }.flow.cachedIn(viewModelScope)

    var stateUiObserver by mutableStateOf<UiState>(UiState.LoadingData(10))
        private set

    fun updateUiState(lazyPagingItems: LazyPagingItems<BeersDomain>) {
        val loadState = lazyPagingItems.loadState

        when {
            loadState.append is LoadState.Loading && lazyPagingItems.itemCount > 29 -> {
                stateUiObserver = UiState.LoadingData(repeat = 2)
            }

            (loadState.refresh is LoadState.Error ||
                    loadState.append is LoadState.Error ||
                    loadState.prepend is LoadState.Error) && !loadState.prepend.endOfPaginationReached -> {

                        loadState.getTypeOfError()?.let {
                    val message = it.error.localizedMessage ?: "Error desconocido"
                    stateUiObserver = UiState.ShowMessage(message)
                }
            }

            loadState.prepend.endOfPaginationReached -> {
                stateUiObserver = UiState.ShowMessage(Constants.ERROR_END_LIST)
            }
        }
    }
}