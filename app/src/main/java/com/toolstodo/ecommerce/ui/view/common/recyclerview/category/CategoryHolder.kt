package com.toolstodo.ecommerce.ui.view.common.recyclerview.category

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.toolstodo.ecommerce.R
import com.toolstodo.ecommerce.databinding.CategoryItemBinding
import com.toolstodo.ecommerce.domain.model.category.Category

class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = CategoryItemBinding.bind(itemView)

    fun render(category: Category) {
        with(binding) {
            txtCategory.text = category.name
            validateSelected(category.isSelected)
        }
    }

    private fun validateSelected(isSelected: Boolean){
        with(binding){
            if (isSelected) {
                cvCategory.setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.accent
                    )
                )

                txtCategory.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.text_color_accent
                    )
                )
            } else {
                cvCategory.setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.primary
                    )
                )

                txtCategory.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.text_color_primary
                    )
                )
            }
        }
    }

}