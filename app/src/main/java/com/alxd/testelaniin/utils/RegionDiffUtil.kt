package com.alxd.testelaniin.utils

import androidx.recyclerview.widget.DiffUtil
import com.alxd.testelaniin.model.region.Results

class RegionDiffUtil (private val oldList: List<Results>,
                      private val newList: List<Results>): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
                && oldList[oldItemPosition].url == newList[newItemPosition].url
    }
}