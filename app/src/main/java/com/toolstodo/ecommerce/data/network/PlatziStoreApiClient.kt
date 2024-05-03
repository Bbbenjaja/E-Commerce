package com.toolstodo.ecommerce.data.network

import com.toolstodo.ecommerce.data.model.CategoryModel
import com.toolstodo.ecommerce.data.model.ProductModel
import retrofit2.http.GET
import retrofit2.http.Path

interface PlatziStoreApiClient {

    @GET("products")
    suspend fun fetchProducts() : List<ProductModel>

    @GET("products/{id}")
    suspend fun fetchProductById(@Path("id") id: Int): ProductModel

    @GET("categories")
    suspend fun fetchCategories(): List<CategoryModel>

}