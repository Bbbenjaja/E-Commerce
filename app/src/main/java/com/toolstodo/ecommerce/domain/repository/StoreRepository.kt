package com.toolstodo.ecommerce.domain.repository

import com.toolstodo.ecommerce.data.model.ProductModel
import com.toolstodo.ecommerce.data.model.ResponseInfoModel


interface StoreRepository {

    suspend fun getInfoResponse(limit: Int, skip: Int): ResponseInfoModel

    suspend fun getProductById(id: Int): ProductModel

    suspend fun getCategories(): List<String>

    suspend fun getProductsInCategory(category: String, limit: Int, skip: Int): ResponseInfoModel

    suspend fun getProductsByName(name: String, limit: Int, skip: Int): ResponseInfoModel

}