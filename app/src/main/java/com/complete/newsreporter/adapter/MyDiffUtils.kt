package com.complete.newsreporter.adapter

import androidx.recyclerview.widget.DiffUtil
import com.complete.newsreporter.model.Article

class MyDiffUtils(
    private val oldLs:List<Article>,
    private val newLs:List<Article>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldLs.size
    }

    override fun getNewListSize(): Int {
        return newLs.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldLs[oldItemPosition] == newLs[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldLs[oldItemPosition].equals(newLs[newItemPosition])
    }
}