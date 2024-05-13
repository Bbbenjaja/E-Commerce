package com.toolstodo.ecommerce.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.toolstodo.ecommerce.data.local.dao.favorite.FavoriteDao
import com.toolstodo.ecommerce.data.local.dao.suggestion.SuggestionDao
import com.toolstodo.ecommerce.data.local.entities.FavoriteEntity
import com.toolstodo.ecommerce.data.local.entities.SuggestionEntity

@Database(entities = [SuggestionEntity::class, FavoriteEntity::class], version = 1)
abstract class StoreDatabase: RoomDatabase() {

    abstract fun getSuggestionDao(): SuggestionDao

    abstract fun getFavoriteDao(): FavoriteDao

}