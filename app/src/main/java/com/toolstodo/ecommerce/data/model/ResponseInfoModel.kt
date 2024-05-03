package com.toolstodo.ecommerce.data.model

import com.google.gson.annotations.SerializedName
import com.toolstodo.ecommerce.domain.model.ResponseInfo

data class ResponseInfoModel(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("products")
    val products: List<ProductModel>,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("total")
    val total: Int
)

fun ResponseInfoModel.toDomain(): ResponseInfo {
    return ResponseInfo(limit = limit, skip = skip, total = total, products = products.map { it.toDomain() })
}