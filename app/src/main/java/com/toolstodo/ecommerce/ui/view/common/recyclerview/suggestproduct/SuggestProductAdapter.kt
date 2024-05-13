package com.toolstodo.ecommerce.ui.view.common.recyclerview.suggestproduct

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.toolstodo.ecommerce.R
import com.toolstodo.ecommerce.domain.model.product.Product
import com.toolstodo.ecommerce.ui.view.common.recyclerview.product.ProductDiffUtil

class SuggestProductAdapter constructor(
    private var productList: List<Product>,
    private val onProductClick: (Product) -> Unit,
) : RecyclerView.Adapter<SuggestProductHolder>() {

    fun updateList(newList: List<Product>){
        val diffUtil = ProductDiffUtil(oldList = productList, newList = newList)
        val result = DiffUtil.calculateDiff(diffUtil)
        productList = newList
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestProductHolder {
        return SuggestProductHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.product_suggest_item, parent, false),
            onProductClick
        )
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: SuggestProductHolder, position: Int) {
        holder.render(productList[position])
    }
}