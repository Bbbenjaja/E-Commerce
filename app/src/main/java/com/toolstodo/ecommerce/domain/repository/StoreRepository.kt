package com.toolstodo.ecommerce.domain.repository

import com.toolstodo.ecommerce.data.model.CategoryModel
import com.toolstodo.ecommerce.data.model.ProductModel

interface StoreRepository {

    suspend fun getAllProducts(): List<ProductModel>

    suspend fun getProductById(id: Int): ProductModel

    suspend fun getCategories(): List<CategoryModel>

}