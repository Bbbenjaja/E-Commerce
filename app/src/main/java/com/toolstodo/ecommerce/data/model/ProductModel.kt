package com.toolstodo.ecommerce.data.model

import com.google.gson.annotations.SerializedName
import com.toolstodo.ecommerce.domain.model.Product

data class ProductModel(
    @SerializedName("brand")
    val brand: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("discountPercentage")
    val discountPercentage: Double,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("price")
    val price: Int,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("stock")
    val stock: Int,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("title")
    val title: String
)

fun ProductModel.toDomain(): Product{
    return Product(brand, category, description, discountPercentage, id, images, price, rating, stock, thumbnail, title)
}