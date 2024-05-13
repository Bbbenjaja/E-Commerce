package com.toolstodo.ecommerce.data.repository

import com.toolstodo.ecommerce.data.local.dao.favorite.FavoriteDao
import com.toolstodo.ecommerce.data.local.dao.suggestion.SuggestionDao
import com.toolstodo.ecommerce.data.local.entities.FavoriteEntity
import com.toolstodo.ecommerce.data.local.entities.SuggestionEntity
import com.toolstodo.ecommerce.data.model.ProductModel
import com.toolstodo.ecommerce.data.model.ResponseInfoModel
import com.toolstodo.ecommerce.data.network.DummyStoreApiClient
import com.toolstodo.ecommerce.domain.repository.StoreRepository
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val dummyStoreApiClient: DummyStoreApiClient,
    private val suggestionDao: SuggestionDao,
    private val favoriteDao: FavoriteDao
): StoreRepository {
    override suspend fun getInfoResponse(limit: Int, skip: Int): ResponseInfoModel {
        return dummyStoreApiClient.fetchProducts(limit = limit, skip = skip)
    }

    override suspend fun getProductById(id: Int): ProductModel {
        TODO("Not yet implemented")
    }

    override suspend fun getCategories(): List<String> {
        return dummyStoreApiClient.fetchCategories()
    }

    override suspend fun getProductsInCategory(
        category: String,
        limit: Int,
        skip: Int,
    ): ResponseInfoModel {
        return dummyStoreApiClient.fetchProductsInCategory(category, limit, skip)
    }

    override suspend fun getProductsByName(name: String, limit: Int, skip: Int): ResponseInfoModel {
        return dummyStoreApiClient.fetchProductsByName(name, limit = limit, skip = skip)
    }

    override suspend fun getAllSuggestions(): List<SuggestionEntity> {
        return suggestionDao.getAllSuggestions()
    }

    override suspend fun insertSuggestions(suggestions: List<SuggestionEntity>) {
        suggestionDao.insertAll(suggestions)
    }

    override suspend fun deleteAllSuggestions() {
        suggestionDao.deleteAllSuggestions()
    }

    override suspend fun insertFavorite(favorite: FavoriteEntity) {
        favoriteDao.insertOne(favorite)
    }

    override suspend fun getAllFavorites(): List<FavoriteEntity> {
        return favoriteDao.getAllFavorites()
    }

    override suspend fun verifyFav(id: Int): Boolean {
        return favoriteDao.isFavorite(id = id)
    }

    override suspend fun deleteFavorite(id: Int) {
        favoriteDao.deleteFav(id)
    }


}