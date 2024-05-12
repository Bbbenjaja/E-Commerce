package com.toolstodo.ecommerce.domain.usecases

import com.toolstodo.ecommerce.data.model.toDomain
import com.toolstodo.ecommerce.data.repository.StoreRepositoryImpl
import com.toolstodo.ecommerce.domain.model.product.ResponseInfo
import javax.inject.Inject

class GetProductsByNameUseCase @Inject constructor(
    private val storeRepositoryImpl: StoreRepositoryImpl,
) {

    suspend operator fun invoke(name: String, limit: Int, skip: Int): ResponseInfo {
        return storeRepositoryImpl.getProductsByName(name = name, limit = limit, skip = skip)
            .toDomain()
    }

}