package com.example.pruebatecnicaliverpool.utils

import com.example.pruebatecnicaliverpool.domain.model.SortModel

object Constants {
    const val BASE_URL = "https://shoppapp.liverpool.com.mx"
    const val PLP_ENDPOINT = "/appclienteservices/services/v3/plp"
    const val DEFAULT_URL = "https://www.liderlogo.es/wp-content/uploads/2022/12/Logo-Android-1024x640.png"

    enum class TypeSort(val s: String) {
        DEFAULT("predefinida"),
        LOWEST_PRICE("menor precio"),
        HIGHER_PRICE("mayor precio"),
        CANCEL("")
    }

    val listSortType = listOf<SortModel>(
        SortModel(TypeSort.DEFAULT.s, TypeSort.DEFAULT),
        SortModel(TypeSort.LOWEST_PRICE.s, TypeSort.LOWEST_PRICE),
        SortModel(TypeSort.HIGHER_PRICE.s, TypeSort.HIGHER_PRICE),
    )
}
