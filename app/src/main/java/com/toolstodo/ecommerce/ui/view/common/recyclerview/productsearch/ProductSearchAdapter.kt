package com.toolstodo.ecommerce.ui.view.common.recyclerview.productsearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.toolstodo.ecommerce.R
import com.toolstodo.ecommerce.domain.model.product.Product
import com.toolstodo.ecommerce.ui.view.common.recyclerview.product.ProductDiffUtil

class ProductSearchAdapter constructor(
    private var productList: List<Product>,
) : RecyclerView.Adapter<ProductSearchHolder>() {

    fun updateList(newList: List<Product>) {
        val diffUtil = ProductDiffUtil(productList, newList)
        val result = DiffUtil.calculateDiff(diffUtil)
        productList = newList
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductSearchHolder {
        return ProductSearchHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_search_item, parent, false)
        )
    }

    override fun getItemCount(): Int = productList.size


    override fun onBindViewHolder(holder: ProductSearchHolder, position: Int) {
        holder.render(product = productList[position])
    }
}