package com.toolstodo.ecommerce.data.model

import com.google.gson.annotations.SerializedName
import com.toolstodo.ecommerce.domain.model.Product

data class ProductModel(
    @SerializedName("category")
    val category: CategoryModel,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("price")
    val price: Int,
    @SerializedName("title")
    val title: String,
)

fun ProductModel.toDomain(): Product =
    Product(
        category = category.toDomain(),
        description = description,
        id = id,
        images = images,
        price = price,
        title = title
    )