package com.toolstodo.ecommerce.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.toolstodo.ecommerce.data.local.entities.SuggestionEntity

@Dao
interface SuggestionDao {

    @Query("SELECT * FROM suggestion_table")
    suspend fun getAllSuggestions(): List<SuggestionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(suggestions: List<SuggestionEntity>)

    @Query("DELETE FROM suggestion_table")
    suspend fun deleteAllSuggestions()

}