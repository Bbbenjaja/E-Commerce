package com.toolstodo.ecommerce.domain.model.product

import java.io.Serializable

data class Product(
    val brand: String,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    val title: String,
    var isFavorite: Boolean = false
): Serializable