package com.toolstodo.ecommerce.data.local.dao.favorite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.toolstodo.ecommerce.data.local.entities.FavoriteEntity
import retrofit2.http.DELETE

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite_table")
    suspend fun getAllFavorites(): List<FavoriteEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_table WHERE id = :id)")
    suspend fun isFavorite(id: Int) : Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(favorite: FavoriteEntity)

    @Query("DELETE FROM favorite_table WHERE id = :id")
    suspend fun deleteFav(id: Int)

}