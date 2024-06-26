package com.toolstodo.ecommerce.domain.repository

import com.toolstodo.ecommerce.data.local.entities.FavoriteEntity
import com.toolstodo.ecommerce.data.local.entities.SuggestionEntity
import com.toolstodo.ecommerce.data.model.ProductModel
import com.toolstodo.ecommerce.data.model.ResponseInfoModel


interface StoreRepository {

    suspend fun getInfoResponse(limit: Int, skip: Int): ResponseInfoModel

    suspend fun getProductById(id: Int): ProductModel

    suspend fun getCategories(): List<String>

    suspend fun getProductsInCategory(category: String, limit: Int, skip: Int): ResponseInfoModel

    suspend fun getProductsByName(name: String, limit: Int, skip: Int): ResponseInfoModel

    suspend fun getAllSuggestions(): List<SuggestionEntity>

    suspend fun insertSuggestions(suggestions: List<SuggestionEntity>)

    suspend fun deleteAllSuggestions()

    suspend fun insertFavorite(favorite: FavoriteEntity)

    suspend fun getAllFavorites(): List<FavoriteEntity>

    suspend fun verifyFav(id: Int): Boolean

    suspend fun deleteFavorite(id: Int)

}