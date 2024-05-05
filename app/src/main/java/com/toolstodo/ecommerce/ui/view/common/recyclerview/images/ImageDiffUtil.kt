package com.toolstodo.ecommerce.ui.view.common.recyclerview.images

import androidx.recyclerview.widget.DiffUtil

class ImageDiffUtil constructor(
    private val oldList: List<String>,
    private val newList: List<String>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition].javaClass == oldList[oldItemPosition].javaClass
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition].hashCode() == oldList[oldItemPosition].hashCode()
    }
}