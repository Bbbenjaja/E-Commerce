package com.toolstodo.ecommerce.ui.view.common.recyclerview.suggestproduct

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.toolstodo.ecommerce.databinding.ProductSuggestItemBinding
import com.toolstodo.ecommerce.domain.model.product.Product

class SuggestProductHolder(
    itemView: View,
    private val onProductClick: (Product) -> Unit,
) :
    RecyclerView.ViewHolder(itemView) {

    private val binding = ProductSuggestItemBinding.bind(itemView)

    fun render(product: Product) {
        with(binding) {
            imgProduct.post {
                Picasso.get().load(product.thumbnail).centerCrop()
                    .resize(imgProduct.width, imgProduct.height)
                    .into(imgProduct)
            }

            txtTitle.text = product.title
        }

        itemView.setOnClickListener {
            onProductClick(product)
        }
    }

}