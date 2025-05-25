package com.example.pruebatecnicaliverpool.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pruebatecnicaliverpool.domain.model.ProductsDomain
import com.example.pruebatecnicaliverpool.domain.repositories.ApiRepository
import com.example.pruebatecnicaliverpool.utils.extensions.getMinSortPrice
import com.example.pruebatecnicaliverpool.utils.NetworkStatusObserver

class PagingDataSource(
    val apiRepository: ApiRepository,
    val properties: SortProperties,
    val networkStatusObserver: Boolean
) :
    PagingSource<Int, ProductsDomain>() {
    override fun getRefreshKey(state: PagingState<Int, ProductsDomain>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductsDomain> {
        return try {
            if (networkStatusObserver) {
                val nextPageNumber = params.key ?: 1
                val response = apiRepository.fetchData(
                    properties.sortType,
                    nextPageNumber,
                    minSortPrice = properties.sortType.getMinSortPrice()
                )
                val data = response.data ?: emptyList()
                LoadResult.Page(
                    data = data,
                    prevKey = null,
                    nextKey = if (data.isNotEmpty()) nextPageNumber + 1 else null
                )
            } else {
                LoadResult.Error(
                    Exception("Error de red. Revisa tu conexión e intenta nuevamente.")
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(
                Exception("Error desconocido: ${e.localizedMessage}")
            )
        }
    }
}


data class SortProperties(
    var sortType: String
)