package com.toolstodo.ecommerce.domain.usecases

import com.toolstodo.ecommerce.data.local.entities.toDomain
import com.toolstodo.ecommerce.data.repository.StoreRepositoryImpl
import com.toolstodo.ecommerce.domain.model.suggestion.Suggestion
import javax.inject.Inject

class GetAllSuggestionsUseCase @Inject constructor(
    private val storeRepositoryImpl: StoreRepositoryImpl,
) {

    suspend operator fun invoke(): List<Suggestion> {
        return storeRepositoryImpl.getAllSuggestions().map {
            it.toDomain()
        }
    }

}