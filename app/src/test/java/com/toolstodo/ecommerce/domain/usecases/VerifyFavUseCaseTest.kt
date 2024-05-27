package com.toolstodo.ecommerce.domain.usecases

import com.toolstodo.ecommerce.data.repository.StoreRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class VerifyFavUseCaseTest {

    @RelaxedMockK
    private lateinit var storeRepositoryImpl: StoreRepositoryImpl

    private lateinit var verifyFavUseCase: VerifyFavUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        verifyFavUseCase = VerifyFavUseCase(storeRepositoryImpl)
    }

    @Test
    fun `if product is in favorite return true`() = runBlocking {
        //Given
        coEvery {
            storeRepositoryImpl.verifyFav(1)
        } returns true

        //When
        verifyFavUseCase(1)

        //Then
        coVerify(exactly = 1) {
            storeRepositoryImpl.verifyFav(1)
        }

    }

}