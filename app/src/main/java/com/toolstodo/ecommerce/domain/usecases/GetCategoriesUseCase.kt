package com.toolstodo.ecommerce.domain.usecases

import com.toolstodo.ecommerce.data.repository.StoreRepositoryImpl
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val storeRepositoryImpl: StoreRepositoryImpl
) {

    suspend operator fun invoke(): List<String> {
        return storeRepositoryImpl.getCategories()
    }

}