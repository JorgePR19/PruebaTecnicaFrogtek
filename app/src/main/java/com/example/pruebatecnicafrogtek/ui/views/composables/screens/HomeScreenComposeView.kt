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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pruebatecnicafrogtek.R
import com.example.pruebatecnicafrogtek.ui.views.MainViewModel
import com.example.pruebatecnicafrogtek.ui.views.composables.components.ItemView
import com.example.pruebatecnicafrogtek.ui.views.composables.components.Skeleton


@Composable
fun HomeScreenComposeView(mainViewModel: MainViewModel, modifier: Modifier) {
    val lazyListState = rememberLazyGridState()
    val lazyPagingItems = mainViewModel.beersPaging.collectAsLazyPagingItems()

    LazyVerticalGrid(
        modifier = modifier,
        state = lazyListState,
        columns = GridCells.Fixed(2),
    ) {
        when {
            lazyPagingItems.loadState.isIdle || lazyPagingItems.loadState.append is LoadState.Loading -> {
                if (lazyPagingItems.itemCount > 0) {
                    items(lazyPagingItems.itemCount) {
                        val itemsPag = lazyPagingItems[it]
                        if (itemsPag != null) {
                            ItemView(itemsPag)
                        }
                    }

                    when (val appendState = lazyPagingItems.loadState.append) {
                        is LoadState.Error -> {
                            item {
                                Text(
                                    stringResource(R.string.error_append),
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        LoadState.Loading -> {
                            if (lazyPagingItems.itemCount > 30) {
                                items(2) {
                                    Skeleton()
                                }
                            }
                        }

                        is LoadState.NotLoading -> {
                            if (appendState.endOfPaginationReached) {
                                item(span = { GridItemSpan(maxLineSpan) })  {
                                    Box(
                                        Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = stringResource(R.string.end_list),
                                            style = MaterialTheme.typography.bodyMedium,
                                            modifier = Modifier.fillMaxWidth(),
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                }
                            }
                        }
                    }
                } else {
                    item(span = { GridItemSpan(maxLineSpan) })  {
                        Box(
                            Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            Text(
                                text = stringResource(R.string.empty_list),
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }

            lazyPagingItems.loadState.refresh is LoadState.Loading -> {
                items(10) {
                    Skeleton()
                }
            }

            lazyPagingItems.loadState.refresh is LoadState.Error -> {
                val errorMessage =
                    (lazyPagingItems.loadState.refresh as LoadState.Error).error.localizedMessage

                item(span = { GridItemSpan(maxLineSpan) }) {
                    Box(
                        Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Text(
                            text = errorMessage ?: stringResource(R.string.error_append),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            else -> {
                if (lazyPagingItems.itemCount > 29) {
                    items(2) {
                        Skeleton()
                    }
                }
            }
        }
    }
}