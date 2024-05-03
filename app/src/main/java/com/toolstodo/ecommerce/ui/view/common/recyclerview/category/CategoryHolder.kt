package com.toolstodo.ecommerce.ui.view.common.recyclerview.category

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.toolstodo.ecommerce.databinding.CategoryItemBinding
import com.toolstodo.ecommerce.domain.model.Category

class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = CategoryItemBinding.bind(itemView)

    fun render(category: Category) {
        with(binding) {
            imgCategory.post {
                Picasso.get().load(category.image).centerCrop()
                    .resize(imgCategory.width, imgCategory.height).into(imgCategory)
            }

            txtCategory.text = category.name
        }
    }

}