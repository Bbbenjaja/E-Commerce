package com.toolstodo.ecommerce.di

import android.content.Context
import androidx.room.Room
import com.toolstodo.ecommerce.data.local.StoreDatabase
import com.toolstodo.ecommerce.data.local.dao.favorite.FavoriteDao
import com.toolstodo.ecommerce.data.local.dao.suggestion.SuggestionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    private const val STORE_DB_NAME = "suggestion_database"

    @Singleton
    @Provides
    fun provideStoreDB(@ApplicationContext context: Context): StoreDatabase {
        return Room.databaseBuilder(context, StoreDatabase::class.java, STORE_DB_NAME).build()
    }

    @Singleton
    @Provides
    fun provideSuggestionDao(db: StoreDatabase): SuggestionDao {
        return db.getSuggestionDao()
    }

    @Singleton
    @Provides
    fun provideFavoriteDao(db: StoreDatabase): FavoriteDao {
        return db.getFavoriteDao()
    }

}