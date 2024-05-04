package com.toolstodo.ecommerce.ui.view.common.recyclerview.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.toolstodo.ecommerce.R
import com.toolstodo.ecommerce.domain.model.category.Category
import java.util.Locale

class CategoryAdapter constructor(
    private var categoryList: List<Category>,
) : RecyclerView.Adapter<CategoryHolder>() {

    fun updateList(newList: List<Category>) {
        val diffUtil = CategoryDiffUtil(categoryList, newList)
        val result = DiffUtil.calculateDiff(diffUtil)
        categoryList = newList
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        return CategoryHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.render(categoryList[position])
    }
}