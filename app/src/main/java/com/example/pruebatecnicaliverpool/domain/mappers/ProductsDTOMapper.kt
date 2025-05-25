package com.example.pruebatecnicaliverpool.domain.mappers

import com.example.pruebatecnicaliverpool.data.response.PlpRecordsResponse
import com.example.pruebatecnicaliverpool.data.response.PlpResponse
import com.example.pruebatecnicaliverpool.domain.model.ProductsDomain
import com.example.pruebatecnicaliverpool.utils.Constants


class ProductsDTOMapper {

    fun fromDtoToDomain(record: PlpRecordsResponse): ProductsDomain {
        return ProductsDomain(
            productDisplayName = record.productDisplayName,
            listPrice = record.listPrice,
            promoPrice = record.promoPrice,
            lgImage = record.lgImage ?: Constants.DEFAULT_URL,
            variantsColor = record.variantsColor.orEmpty()
                .mapNotNull { it.colorHex.takeIf { hex -> hex.isNotBlank() } }
        )
    }

    fun fromDtoToDomainList(response: PlpResponse): List<ProductsDomain> {
        return response.plpResults.records.map { record ->
            fromDtoToDomain(record)
        }
    }
}