package com.example.pruebatecnicaliverpool.domain.model

data class ProductsDomain(
    val productDisplayName: String,
    val listPrice: Double,
    val promoPrice: Double,
    val lgImage: String,
    val variantsColor: List<String>
)