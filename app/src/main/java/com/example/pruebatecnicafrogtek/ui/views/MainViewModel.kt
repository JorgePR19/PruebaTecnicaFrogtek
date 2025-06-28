package com.example.pruebatecnicafrogtek.ui.views

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.pruebatecnicafrogtek.domain.paging.PagingDataSource
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

    fun updateUiState(newState: UiState) {
        stateUiObserver = newState
    }
}