package com.toolstodo.ecommerce.ui.view.common.recyclerview.category

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.toolstodo.ecommerce.databinding.CategoryItemBinding

class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = CategoryItemBinding.bind(itemView)

    fun render(category: String) {
        with(binding) {
            txtCategory.text = category
        }
    }

}