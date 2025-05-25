package com.example.pruebatecnicaliverpool.domain.repositories

import com.example.pruebatecnicaliverpool.data.api.ApiContract
import com.example.pruebatecnicaliverpool.data.utilsresponse.ResponseStatus
import com.example.pruebatecnicaliverpool.data.utilsresponse.makeNetWorkCall
import com.example.pruebatecnicaliverpool.domain.mappers.ProductsDTOMapper
import com.example.pruebatecnicaliverpool.domain.model.ProductsDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface ApiTask {
    suspend fun fetchData(
        searchString: String,
        pageNumber: Int,
        minSortPrice: String
    ): ResponseStatus<List<ProductsDomain>>
}

class ApiRepository @Inject constructor(private val apiContract: ApiContract) : ApiTask {
    override suspend fun fetchData(
        searchString: String,
        pageNumber: Int,
        minSortPrice: String
    ): ResponseStatus<List<ProductsDomain>> {
        return withContext(Dispatchers.IO) {
            val productsDeferred = async { getProductsDeferred(searchString, pageNumber, minSortPrice) }
            val productsResponse = productsDeferred.await()
            productsResponse
        }
    }

    private suspend fun getProductsDeferred(
        searchString: String,
        pageNumber: Int,
        minSortPrice: String
    ): ResponseStatus<List<ProductsDomain>> = makeNetWorkCall {
        val response = apiContract.getProducts(searchString,pageNumber,minSortPrice)
        val mapper = ProductsDTOMapper()
        delay(3000)
        mapper.fromDtoToDomainList(response)
    }
}