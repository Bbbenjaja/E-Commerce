package com.toolstodo.ecommerce.ui.view.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toolstodo.ecommerce.domain.model.category.Category
import com.toolstodo.ecommerce.domain.model.product.ResponseInfo
import com.toolstodo.ecommerce.domain.usecases.GetCategoriesUseCase
import com.toolstodo.ecommerce.domain.usecases.GetResponseInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getResponseInfoUseCase: GetResponseInfoUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _infoState = MutableLiveData<ResponseInfo>()
    val infoState get() = _infoState

    private val _categoryState = MutableLiveData<List<Category>>()
    val categoryState get() = _categoryState

    val limit: Int = 20
    var skip: Int = 0

    init {
        fetchResponseInfo(limit = limit, skip = skip)
        fetchCategories()
    }

    fun fetchResponseInfo(limit: Int, skip: Int){
        viewModelScope.launch(Dispatchers.IO) {
            _infoState.postValue(getResponseInfoUseCase(limit = limit, skip = skip))
        }
    }

    fun fetchCategories(){
        viewModelScope.launch(Dispatchers.IO){
            _categoryState.postValue(getCategoriesUseCase())
        }
    }

}