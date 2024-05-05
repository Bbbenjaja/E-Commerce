package com.toolstodo.ecommerce.ui.view.common.recyclerview.images

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.toolstodo.ecommerce.R
import com.toolstodo.ecommerce.databinding.ImageItemBinding

class ImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ImageItemBinding.bind(itemView)

    fun render(urlImage: String, onImgClick: (String) -> Unit) {
        with(binding) {
            imgProduct.post {
                Picasso.get().load(urlImage).centerCrop()
                    .resize(imgProduct.width, imgProduct.height).into(imgProduct)
            }
        }

        itemView.setOnClickListener {
            onImgClick(urlImage)
        }

    }


}