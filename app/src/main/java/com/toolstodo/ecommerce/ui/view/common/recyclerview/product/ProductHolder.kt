package com.toolstodo.ecommerce.ui.view.common.recyclerview.product

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.toolstodo.ecommerce.R
import com.toolstodo.ecommerce.databinding.ProductItemBinding
import com.toolstodo.ecommerce.domain.model.product.Product

class ProductHolder(
    itemView: View,
    private val onProductClick: (Product) -> Unit,
) :
    RecyclerView.ViewHolder(itemView) {

    private val binding = ProductItemBinding.bind(itemView)

    fun render(product: Product) {
        with(binding) {
            imgProduct.post {
                Picasso.get().load(product.thumbnail).centerCrop()
                    .resize(imgProduct.width, imgProduct.height).into(imgProduct)
            }
            txtTitle.text = product.title
            txtDescription.text = product.description
            txtPrice.text = product.price.toString()
            txtRating.text = product.rating.toString()
            txtDiscount.text =
                itemView.context.getString(R.string.discount_text, product.discountPercentage)
        }

        itemView.setOnClickListener {
            onProductClick(product)
        }
    }

}