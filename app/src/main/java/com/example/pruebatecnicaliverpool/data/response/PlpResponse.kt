package com.example.pruebatecnicaliverpool.data.response

import com.google.gson.annotations.SerializedName

data class PlpResponse(
    @SerializedName("plpResults") val plpResults: PlpResults
)

data class PlpResults(
    @SerializedName("records") val records: List<PlpRecordsResponse> = emptyList()
)

data class PlpRecordsResponse(
    @SerializedName("productDisplayName") val productDisplayName: String,
    @SerializedName("listPrice") val listPrice: Double,
    @SerializedName("promoPrice") val promoPrice: Double,
    @SerializedName("smImage") val lgImage: String?,
    @SerializedName("variantsColor") val variantsColor: List<PlpVariantsColor>? = emptyList()
)

data class PlpVariantsColor(
    @SerializedName("colorHex") val colorHex: String
)
