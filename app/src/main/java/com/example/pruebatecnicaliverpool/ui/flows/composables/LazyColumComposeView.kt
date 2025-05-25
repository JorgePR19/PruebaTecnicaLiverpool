package com.example.pruebatecnicaliverpool.ui.flows.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pruebatecnicaliverpool.R
import com.example.pruebatecnicaliverpool.ui.flows.listhomeview.HomeScreenViewModel
import com.example.pruebatecnicaliverpool.utils.Skeleton

@Composable
fun LazyColumComposeView(homeScreenViewModel: HomeScreenViewModel) {
    val lazyListState = rememberLazyListState()
    val lazyPagingItems = homeScreenViewModel.productsPaging.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.padding(16.dp),
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(16.dp)
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
                            if (lazyPagingItems.itemCount > 10) {
                                item {
                                    Skeleton()
                                }
                            }
                        }

                        is LoadState.NotLoading -> {
                            if (appendState.endOfPaginationReached) {
                                item {
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
                    item {
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
                item {
                    repeat(5) {
                        Skeleton()
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }

            lazyPagingItems.loadState.refresh is LoadState.Error -> {
                val errorMessage =
                    (lazyPagingItems.loadState.refresh as LoadState.Error).error.localizedMessage

                item {
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
                if (lazyPagingItems.itemCount > 9) {
                    item {
                        Skeleton()
                    }
                }
            }
        }
    }
}