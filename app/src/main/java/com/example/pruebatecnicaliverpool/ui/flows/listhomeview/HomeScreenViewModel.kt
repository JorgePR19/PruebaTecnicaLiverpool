package com.example.pruebatecnicaliverpool.ui.flows.listhomeview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.pruebatecnicaliverpool.domain.paging.PagingDataSource
import com.example.pruebatecnicaliverpool.domain.paging.SortProperties
import com.example.pruebatecnicaliverpool.domain.repositories.ApiRepository
import com.example.pruebatecnicaliverpool.utils.NetworkStatusObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val networkStatusObserver: NetworkStatusObserver
) : ViewModel() {

    private val _pagingProperties = MutableStateFlow(SortProperties(""))

    fun setPagingValues(currentAccount: String) {
        _pagingProperties.value = SortProperties(
            sortType = currentAccount
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val productsPaging = networkStatusObserver.isConnected
        .flatMapLatest { isConnected ->
            _pagingProperties.flatMapLatest { currentPagingProperties ->
                Pager(
                    PagingConfig(pageSize = 16)
                ) {
                    PagingDataSource(
                        apiRepository = apiRepository,
                        properties = currentPagingProperties,
                        networkStatusObserver = isConnected
                    )
                }.flow
            }
        }
        .cachedIn(viewModelScope)

}