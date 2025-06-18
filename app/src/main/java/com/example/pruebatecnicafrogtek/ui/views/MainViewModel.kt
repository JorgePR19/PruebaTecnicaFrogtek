package com.example.pruebatecnicafrogtek.ui.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.pruebatecnicafrogtek.datasource.paging.PagingDataSource
import com.example.pruebatecnicafrogtek.domain.usecases.PagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: PagingUseCase) : ViewModel() {
    val beersPaging = Pager(PagingConfig(pageSize = 30)) {
        PagingDataSource(useCase)
    }.flow.cachedIn(viewModelScope)
}