package com.example.fitnesskit.presentation.fragments.lessons.adapter.viewHoldres

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.domainn.models.SummaryModel
import com.example.fitnesskit.R
import com.example.fitnesskit.databinding.ItemLayoutLessonsBinding

class LessonNormalViewHolder(private val binding: ItemLayoutLessonsBinding) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(model: SummaryModel) {
        with(binding) {
            timeStart.text = model.startTime
            timeEnd.text = model.endTime
            tittleLesson.text = model.lessonName
            tittleLocation.text = model.place
            viewColor.backgroundTintList = ColorStateList.valueOf(Color.parseColor(model.color))
            if (model.fullNameTrainer == "") {
                iconView.setImageResource(R.drawable.group)
                nameTrainer.visibility = View.INVISIBLE
                slotsAvailable.visibility = View.VISIBLE
                slotsAvailable.text = "Мест: ${model.slots}"
            } else {
                nameTrainer.visibility = View.VISIBLE
                slotsAvailable.visibility = View.INVISIBLE
                nameTrainer.text = model.fullNameTrainer
                iconView.setImageResource(R.drawable.user)
            }
        }
    }
}