package com.toolstodo.ecommerce.ui.view.common.recyclerview.category

import androidx.recyclerview.widget.DiffUtil
import com.toolstodo.ecommerce.domain.model.category.Category

class CategoryDiffUtil constructor(
    private val oldList: List<Category>,
    private val newList: List<Category>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition].javaClass == oldList[oldItemPosition].javaClass
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition].hashCode() == oldList[oldItemPosition].hashCode()
    }
}