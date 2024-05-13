package com.toolstodo.ecommerce.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.toolstodo.ecommerce.data.local.dao.SuggestionDao
import com.toolstodo.ecommerce.data.local.entities.SuggestionEntity

@Database(entities = [SuggestionEntity::class], version = 1)
abstract class SuggestionDatabase: RoomDatabase() {

    abstract fun getSuggestionDao(): SuggestionDao

}