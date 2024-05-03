package com.toolstodo.ecommerce.domain.usecases

import com.toolstodo.ecommerce.data.model.toDomain
import com.toolstodo.ecommerce.data.repository.StoreRepositoryImpl
import com.toolstodo.ecommerce.domain.model.Product
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val storeRepositoryImpl: StoreRepositoryImpl,
) {

    suspend operator fun invoke(): List<Product> {
        return storeRepositoryImpl.getAllProducts().map { it.toDomain() }
    }

}