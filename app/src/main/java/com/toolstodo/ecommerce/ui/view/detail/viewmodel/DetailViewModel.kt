package com.toolstodo.ecommerce.ui.view.detail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toolstodo.ecommerce.domain.model.product.Product
import com.toolstodo.ecommerce.domain.model.product.ResponseInfo
import com.toolstodo.ecommerce.domain.usecases.GetProductsInCategoryUseCase
import com.toolstodo.ecommerce.domain.usecases.ModifyFavUseCase
import com.toolstodo.ecommerce.domain.usecases.VerifyFavUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getProductsInCategoryUseCase: GetProductsInCategoryUseCase,
    private val modifyFavUseCase: ModifyFavUseCase,
    private val verifyFavUseCase: VerifyFavUseCase
): ViewModel() {

    private val _infoState = MutableLiveData<ResponseInfo>()
    val infoState: LiveData<ResponseInfo> get() = _infoState

    private val _favState = MutableLiveData<Boolean>()
    val favState: LiveData<Boolean> get() = _favState

    fun getProductsInCategory(category: String){
        viewModelScope.launch(Dispatchers.IO){
            _infoState.postValue(getProductsInCategoryUseCase(category, 0, 0))
        }
    }

    fun modifyFav(product: Product){
        viewModelScope.launch(Dispatchers.IO){
            modifyFavUseCase(product)
        }
    }

    fun verifyFav(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            _favState.postValue(verifyFavUseCase(id))
        }
    }

}