package com.example.fitnesskit.utills

import androidx.recyclerview.widget.DiffUtil
import com.example.domainn.models.SummaryModel

class LessonsDiffUtils(private val oldList : MutableList<SummaryModel>,
                       private val newList : MutableList<SummaryModel>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList == newList
    }

}