package com.toolstodo.ecommerce.ui.view.common.recyclerview.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.toolstodo.ecommerce.R
import com.toolstodo.ecommerce.domain.model.product.Product

class ProductAdapter constructor(
    private var productList: List<Product>,
    private val onProductClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductHolder>() {

    fun updateList(newList: List<Product>) {
        val diffCallback = ProductDiffUtil(oldList = productList, newList = newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        productList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return ProductHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false),
            onProductClick
        )
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.render(productList[position])
    }
}