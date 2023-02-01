package com.example.fitnesskit.presentation.fragments.lessons.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.domainn.models.SummaryModel
import com.example.fitnesskit.databinding.ItemLayoutLessonsBinding
import com.example.fitnesskit.databinding.ItemLayoutLessonsWithTittleBinding
import com.example.fitnesskit.presentation.fragments.lessons.adapter.viewHoldres.LessonNormalViewHolder
import com.example.fitnesskit.presentation.fragments.lessons.adapter.viewHoldres.LessonTittleViewHolder
import com.example.fitnesskit.utills.LessonsDiffUtils
import com.example.fitnesskit.utills.NORMAL
import com.example.fitnesskit.utills.WITH_TITTLE

class LessonAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var mList = mutableListOf<SummaryModel>()

    override fun getItemViewType(position: Int): Int {
        return if(mList[position].showTittle){
            WITH_TITTLE
        }
        else{
            NORMAL
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if(viewType == NORMAL){
            val binding = ItemLayoutLessonsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            LessonNormalViewHolder(binding)
        } else{
            val binding = ItemLayoutLessonsWithTittleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            LessonTittleViewHolder(binding)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(holder.itemViewType == NORMAL){
            (holder as LessonNormalViewHolder).bind(mList[position])
        }
        else{
            (holder as LessonTittleViewHolder).bind(mList[position])
        }
    }

    fun setList(list: MutableList<SummaryModel>) {
        val diffUtil = LessonsDiffUtils(mList, list)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        mList.clear()
        mList.addAll(list)
        diffResults.dispatchUpdatesTo(this)
    }
}