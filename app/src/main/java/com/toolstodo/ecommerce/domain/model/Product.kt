package com.toolstodo.ecommerce.domain.model


data class Product(
    val category: Category,
    val description: String,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val title: String
)