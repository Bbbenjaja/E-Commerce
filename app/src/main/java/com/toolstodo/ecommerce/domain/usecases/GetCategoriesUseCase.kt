package com.toolstodo.ecommerce.domain.usecases

import com.toolstodo.ecommerce.data.repository.StoreRepositoryImpl
import com.toolstodo.ecommerce.domain.model.category.Category
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val storeRepositoryImpl: StoreRepositoryImpl
) {

    suspend operator fun invoke(): List<Category> {
        return storeRepositoryImpl.getCategories().map { Category(it, false) }
    }

}