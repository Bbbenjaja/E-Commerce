package com.toolstodo.ecommerce.ui.view.search.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toolstodo.ecommerce.domain.model.product.ResponseInfo
import com.toolstodo.ecommerce.domain.model.suggestion.Suggestion
import com.toolstodo.ecommerce.domain.usecases.GetProductsByNameUseCase
import com.toolstodo.ecommerce.domain.usecases.InsertSuggestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getProductsByNameUseCase: GetProductsByNameUseCase,
    private val insertSuggestionsUseCase: InsertSuggestionsUseCase,
) : ViewModel() {

    private val _infoState = MutableLiveData<ResponseInfo>()
    val infoState get() = _infoState

    var limit = 0
    var skip = 0
    var query = ""

    fun getProductsByName(name: String, limit: Int, skip: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _infoState.postValue(getProductsByNameUseCase(name, limit = limit, skip = skip))
        }
    }

    fun insertSuggestions(suggestions: List<Suggestion>){
        viewModelScope.launch(Dispatchers.IO){
            insertSuggestionsUseCase(suggestions)
        }
    }

}