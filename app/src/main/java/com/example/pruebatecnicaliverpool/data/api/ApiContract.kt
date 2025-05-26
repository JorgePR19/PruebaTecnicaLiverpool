package com.example.pruebatecnicaliverpool.data.api

import com.example.pruebatecnicaliverpool.data.response.PlpResponse
import com.example.pruebatecnicaliverpool.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiContract {
    @GET(Constants.PLP_ENDPOINT)
    suspend fun getProducts(
        @Query("search-string") searchString: String,
        @Query("page-number") pageNumber: Int,
        @Query("sortBy") sortBy: String
    ): PlpResponse
}