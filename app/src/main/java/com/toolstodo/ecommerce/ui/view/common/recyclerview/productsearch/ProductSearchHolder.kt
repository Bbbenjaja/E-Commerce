package com.toolstodo.ecommerce.ui.view.common.recyclerview.productsearch

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.toolstodo.ecommerce.databinding.ProductSearchItemBinding
import com.toolstodo.ecommerce.domain.model.product.Product

class ProductSearchHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ProductSearchItemBinding.bind(itemView)

    fun render(product: Product) {
        with(binding) {
            imgProduct.post {
                Picasso.get().load(product.thumbnail).centerCrop()
                    .resize(imgProduct.width, imgProduct.height)
                    .into(imgProduct)
            }

            txtTitle.text = product.title
            txtBrand.text = product.brand
            txtPrice.text = product.price.toString()
            txtRating.text = product.rating.toString()

        }
    }

}