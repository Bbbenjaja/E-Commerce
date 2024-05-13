package com.toolstodo.ecommerce.domain.usecases

import com.toolstodo.ecommerce.data.local.entities.FavoriteEntity
import com.toolstodo.ecommerce.data.repository.StoreRepositoryImpl
import com.toolstodo.ecommerce.domain.model.product.Product
import javax.inject.Inject

class ModifyFavUseCase @Inject constructor(
    private val storeRepositoryImpl: StoreRepositoryImpl
) {

    suspend operator fun invoke(product: Product){
        if (product.isFavorite) {
            storeRepositoryImpl.insertFavorite(FavoriteEntity(product.id))
        }else{
            storeRepositoryImpl.deleteFavorite(product.id)
        }
    }

}