package com.example.taskthreeweeklyseven.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.taskthreeweeklyseven.data.model.Cat

class DiffUtilCallback(
    private val oldList: List<Cat>,
    private val newList: List<Cat>,
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].url == oldList[newItemPosition].url

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}