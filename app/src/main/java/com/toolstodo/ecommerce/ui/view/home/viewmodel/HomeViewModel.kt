package com.toolstodo.ecommerce.ui.view.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toolstodo.ecommerce.domain.model.Product
import com.toolstodo.ecommerce.domain.usecases.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
) : ViewModel() {

    private val _productState: MutableLiveData<List<Product>> = MutableLiveData<List<Product>>()
    val productState get() = _productState

    init {
        fetchProducts()
    }

    fun fetchProducts(){
        viewModelScope.launch(Dispatchers.IO) {
            _productState.postValue(getProductsUseCase())
        }
    }

}