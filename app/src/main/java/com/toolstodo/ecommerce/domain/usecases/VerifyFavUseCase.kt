package com.toolstodo.ecommerce.domain.usecases

import com.toolstodo.ecommerce.data.repository.StoreRepositoryImpl
import javax.inject.Inject

class VerifyFavUseCase @Inject constructor(
    private val storeRepositoryImpl: StoreRepositoryImpl
) {

    suspend operator fun invoke(id: Int): Boolean {
        return storeRepositoryImpl.verifyFav(id)
    }

}