package com.toolstodo.ecommerce.ui.view.common.recyclerview.images

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.toolstodo.ecommerce.R

class ImageAdapter constructor(
    private var imgUrls: List<String>,
    private val onImageClick: (String) -> Unit,
) : RecyclerView.Adapter<ImageHolder>() {

    fun updateList(newList: List<String>) {
        val imgDiffUtil = ImageDiffUtil(oldList = imgUrls, newList = newList)
        val result = DiffUtil.calculateDiff(imgDiffUtil)
        imgUrls = newList
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        return ImageHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return imgUrls.size
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.render(imgUrls[position], onImageClick)
    }
}