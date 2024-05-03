package com.toolstodo.ecommerce.ui.view.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toolstodo.ecommerce.domain.model.Category
import com.toolstodo.ecommerce.domain.model.Product
import com.toolstodo.ecommerce.domain.usecases.GetCategoriesUseCase
import com.toolstodo.ecommerce.domain.usecases.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _productState = MutableLiveData<List<Product>>()
    val productState get() = _productState

    private val _categoryState = MutableLiveData<List<Category>>()
    val categoryState get() = _categoryState

    init {
        fetchProducts()
        fetchCategories()
    }

    fun fetchProducts(){
        viewModelScope.launch(Dispatchers.IO) {
            _productState.postValue(getProductsUseCase())
        }
    }

    fun fetchCategories(){
        viewModelScope.launch(Dispatchers.IO){
            _categoryState.postValue(getCategoriesUseCase())
        }
    }

}