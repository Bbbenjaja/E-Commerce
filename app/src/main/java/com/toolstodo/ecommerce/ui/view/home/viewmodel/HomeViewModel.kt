package com.toolstodo.ecommerce.ui.view.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toolstodo.ecommerce.domain.model.category.Category
import com.toolstodo.ecommerce.domain.model.product.ResponseInfo
import com.toolstodo.ecommerce.domain.model.suggestion.Suggestion
import com.toolstodo.ecommerce.domain.usecases.GetAllSuggestionsUseCase
import com.toolstodo.ecommerce.domain.usecases.GetCategoriesUseCase
import com.toolstodo.ecommerce.domain.usecases.GetProductsInCategoryUseCase
import com.toolstodo.ecommerce.domain.usecases.GetResponseInfoUseCase
import com.toolstodo.ecommerce.domain.usecases.InsertSuggestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getResponseInfoUseCase: GetResponseInfoUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getProductsInCategoryUseCase: GetProductsInCategoryUseCase,
    private val getAllSuggestionsUseCase: GetAllSuggestionsUseCase,
) : ViewModel() {

    private val _infoState = MutableLiveData<ResponseInfo>()
    val infoState get() = _infoState

    private val _categoryState = MutableLiveData<List<Category>>()
    val categoryState get() = _categoryState

    private val _suggestionState = MutableLiveData<List<Suggestion>>()
    val suggestionState get() = _suggestionState

    private val _productSuggestionState = MutableLiveData<ResponseInfo>()
    val productSuggestionState get() = _productSuggestionState

    val limit: Int = 0
    var skip: Int = 0

    init {
        fetchResponseInfo(limit = limit, skip = skip)
        fetchCategories()
    }

    fun fetchResponseInfo(limit: Int, skip: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _infoState.postValue(getResponseInfoUseCase(limit = limit, skip = skip))
        }
    }

    fun fetchCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            _categoryState.postValue(getCategoriesUseCase())
        }
    }

    fun fetchProductsInCategory(category: String, limit: Int, skip: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _infoState.postValue(getProductsInCategoryUseCase(category, limit, skip))
        }
    }

    fun refreshCategorySelected(categories: List<Category>) {
        viewModelScope.launch(Dispatchers.IO) {
            _categoryState.postValue(categories)
        }
    }

    fun getAllSuggestions() {
        viewModelScope.launch(Dispatchers.IO) {
            _suggestionState.postValue(getAllSuggestionsUseCase())
        }
    }

    fun getProductsBySuggestions(categorySuggest: String, limit: Int, skip: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _productSuggestionState.postValue(getProductsInCategoryUseCase(categorySuggest, limit, skip))
        }
    }

}