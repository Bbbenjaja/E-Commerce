package com.toolstodo.ecommerce.domain.usecases

import com.toolstodo.ecommerce.data.model.toDomain
import com.toolstodo.ecommerce.data.repository.StoreRepositoryImpl
import com.toolstodo.ecommerce.domain.model.ResponseInfo
import javax.inject.Inject

class GetResponseInfoUseCase @Inject constructor(
    private val storeRepositoryImpl: StoreRepositoryImpl
) {

    suspend operator fun invoke(limit: Int, skip: Int): ResponseInfo{
        return storeRepositoryImpl.getInfoResponse(limit = limit, skip = skip).toDomain()
    }

}