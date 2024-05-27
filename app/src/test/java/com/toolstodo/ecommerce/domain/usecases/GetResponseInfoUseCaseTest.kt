package com.toolstodo.ecommerce.domain.usecases

import com.toolstodo.ecommerce.data.model.ResponseInfoModel
import com.toolstodo.ecommerce.data.repository.StoreRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetResponseInfoUseCaseTest{

    @RelaxedMockK
    private lateinit var storeRepositoryImpl: StoreRepositoryImpl

    private lateinit var getResponseInfoUseCase: GetResponseInfoUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getResponseInfoUseCase = GetResponseInfoUseCase(storeRepositoryImpl)
    }

    @Test
    fun `return a product list with total products in API and limit products`() = runBlocking {
        //Given
        coEvery {
            storeRepositoryImpl.getInfoResponse(0,0)
        } returns ResponseInfoModel(0, emptyList(), 0, 0)

        //When
        getResponseInfoUseCase(0,0)

        //Then
        coVerify(exactly = 1) {
            storeRepositoryImpl.getInfoResponse(0,0)
        }

    }

}