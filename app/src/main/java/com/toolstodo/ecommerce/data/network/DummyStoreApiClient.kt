package com.toolstodo.ecommerce.data.network

import com.toolstodo.ecommerce.data.model.ResponseInfoModel
import retrofit2.http.GET
import retrofit2.http.Query

interface DummyStoreApiClient {

    @GET("products")
    suspend fun fetchProducts(@Query("limit") limit: Int, @Query("skip") skip: Int): ResponseInfoModel

    @GET("products/categories")
    suspend fun fetchCategories(): List<String>

}