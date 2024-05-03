package com.toolstodo.ecommerce.domain.model

data class ResponseInfo(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)