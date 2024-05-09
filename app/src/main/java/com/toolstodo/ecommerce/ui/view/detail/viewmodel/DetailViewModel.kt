package com.toolstodo.ecommerce.ui.view.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toolstodo.ecommerce.domain.model.product.ResponseInfo
import com.toolstodo.ecommerce.domain.usecases.GetProductsInCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getProductsInCategoryUseCase: GetProductsInCategoryUseCase
): ViewModel() {

    private val _infoState = MutableLiveData<ResponseInfo>()
    val infoState get() = _infoState

    fun getProductsInCategory(category: String){
        viewModelScope.launch(Dispatchers.IO){
            _infoState.postValue(getProductsInCategoryUseCase(category, 0, 0))
        }
    }

}