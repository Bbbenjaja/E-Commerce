package com.toolstodo.ecommerce.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.toolstodo.ecommerce.data.local.SuggestionDatabase
import com.toolstodo.ecommerce.data.local.dao.SuggestionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    private const val SUGGESTION_DB_NAME = "suggestion_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context): SuggestionDatabase {
        return Room.databaseBuilder(context, SuggestionDatabase::class.java, SUGGESTION_DB_NAME).build()
    }

    @Singleton
    @Provides
    fun provideSuggestionDao(db: SuggestionDatabase): SuggestionDao {
        return db.getSuggestionDao()
    }

}