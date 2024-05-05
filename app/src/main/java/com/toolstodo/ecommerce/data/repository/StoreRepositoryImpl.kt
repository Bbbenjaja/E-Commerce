package com.toolstodo.ecommerce.data.repository

import com.toolstodo.ecommerce.data.model.ProductModel
import com.toolstodo.ecommerce.data.model.ResponseInfoModel
import com.toolstodo.ecommerce.data.network.DummyStoreApiClient
import com.toolstodo.ecommerce.domain.repository.StoreRepository
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val dummyStoreApiClient: DummyStoreApiClient
): StoreRepository {
    override suspend fun getInfoResponse(limit: Int, skip: Int): ResponseInfoModel {
        return dummyStoreApiClient.fetchProducts(limit = limit, skip = skip)
    }

    override suspend fun getProductById(id: Int): ProductModel {
        TODO("Not yet implemented")
    }

    override suspend fun getCategories(): List<String> {
        return dummyStoreApiClient.fetchCategories()
    }

    override suspend fun getProductsInCategory(
        category: String,
        limit: Int,
        skip: Int,
    ): ResponseInfoModel {
        return dummyStoreApiClient.fetchProductsInCategory(category, limit, skip)
    }


}