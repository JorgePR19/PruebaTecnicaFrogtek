package com.example.pruebatecnicafrogtek.ui.views.composables.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pruebatecnicafrogtek.ui.views.MainViewModel
import com.example.pruebatecnicafrogtek.ui.views.UiState
import com.example.pruebatecnicafrogtek.ui.views.composables.components.ItemView
import com.example.pruebatecnicafrogtek.ui.views.composables.components.Skeleton


@Composable
fun HomeScreenComposeView(mainViewModel: MainViewModel, modifier: Modifier = Modifier) {
    val lazyListState = rememberLazyGridState()
    val lazyPagingItems = mainViewModel.beersPaging.collectAsLazyPagingItems()
    val state = mainViewModel.stateUiObserver


    LaunchedEffect(lazyPagingItems.loadState) {
        mainViewModel.updateUiState(lazyPagingItems)
    }

    LazyVerticalGrid(
        modifier = modifier,
        state = lazyListState,
        columns = GridCells.Fixed(2),
    ) {
        items(lazyPagingItems.itemCount) { index ->
            val item = lazyPagingItems[index]
            if (item != null) {
                ItemView(item)
            }
        }

        when (state) {
            is UiState.ShowMessage -> {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Box(
                        Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = state.message,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.DarkGray
                        )
                    }
                }
            }

            is UiState.LoadingData -> {
                items(state.repeat) {
                    Skeleton()
                }
            }
        }
    }
}
