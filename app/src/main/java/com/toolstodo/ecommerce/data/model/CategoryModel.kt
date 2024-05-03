package com.toolstodo.ecommerce.data.model

import com.google.gson.annotations.SerializedName
import com.toolstodo.ecommerce.domain.model.Category

data class CategoryModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
)

fun CategoryModel.toDomain(): Category = Category(id = id, image = image, name = name)