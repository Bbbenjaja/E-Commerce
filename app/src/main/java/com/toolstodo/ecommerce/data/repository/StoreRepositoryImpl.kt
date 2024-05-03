package com.toolstodo.ecommerce.data.repository

import com.toolstodo.ecommerce.data.model.ProductModel
import com.toolstodo.ecommerce.data.network.PlatziStoreApiClient
import com.toolstodo.ecommerce.domain.repository.StoreRepository
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val platziStoreApiClient: PlatziStoreApiClient
): StoreRepository {
    override suspend fun getAllProducts(): List<ProductModel> {
        return platziStoreApiClient.fetchProducts()
    }

    override suspend fun getProductById(id: Int): ProductModel {
        return platziStoreApiClient.fetchProductById(id)
    }
}