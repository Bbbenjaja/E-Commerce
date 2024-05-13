package com.toolstodo.ecommerce.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.toolstodo.ecommerce.domain.model.suggestion.Suggestion

@Entity(tableName = "suggestion_table")
data class SuggestionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") val id: Int = 0,
    @ColumnInfo("category") val category: String,
    @ColumnInfo("querySearched") val querySearched: String,
)

fun SuggestionEntity.toDomain(): Suggestion {
    return Suggestion(category = category, querySearched = querySearched)
}