package com.toolstodo.ecommerce.domain.model.suggestion

import com.toolstodo.ecommerce.data.local.entities.SuggestionEntity


data class Suggestion(
    val category: String,
    val querySearched: String,
)

fun Suggestion.toRoom(): SuggestionEntity{
    return SuggestionEntity(category = category, querySearched = querySearched)
}