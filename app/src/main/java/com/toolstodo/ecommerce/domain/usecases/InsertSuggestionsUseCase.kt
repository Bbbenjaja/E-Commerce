package com.toolstodo.ecommerce.domain.usecases

import com.toolstodo.ecommerce.data.repository.StoreRepositoryImpl
import com.toolstodo.ecommerce.domain.model.suggestion.Suggestion
import com.toolstodo.ecommerce.domain.model.suggestion.toRoom
import javax.inject.Inject

class InsertSuggestionsUseCase @Inject constructor(
    private val storeRepositoryImpl: StoreRepositoryImpl,
) {

    suspend operator fun invoke(suggestions: List<Suggestion>){
        storeRepositoryImpl.deleteAllSuggestions()
        storeRepositoryImpl.insertSuggestions(suggestions.map { it.toRoom() })
    }

}