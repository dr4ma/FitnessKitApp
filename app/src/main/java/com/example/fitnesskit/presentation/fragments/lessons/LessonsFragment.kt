package com.example.fitnesskit.presentation.fragments.lessons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesskit.R
import com.example.fitnesskit.databinding.FragmentLessonsBinding
import com.example.fitnesskit.presentation.fragments.lessons.adapter.LessonAdapter
import com.example.fitnesskit.utills.CheckConnection
import com.example.fitnesskit.utills.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LessonsFragment : Fragment() {

    private val mViewModel: LessonsViewModel by viewModels()

    @Inject
    lateinit var mLessonAdapter : LessonAdapter
    @Inject
    lateinit var mCheckConnection: CheckConnection

    private var binding: FragmentLessonsBinding? = null
    private val mBinding get() = binding!!

    private lateinit var mRecyclerLessons: RecyclerView
    private lateinit var mProgress: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLessonsBinding.inflate(layoutInflater, container, false)
        mBinding.apply {
            mRecyclerLessons = recyclerLessons
            mProgress = progressLessons
        }
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mRecyclerLessons.adapter = mLessonAdapter
        mViewModel.summaryLessons.observe(viewLifecycleOwner){ lessonsList ->
            mProgress.visibility = View.GONE
            mLessonAdapter.setList(lessonsList)
        }

        if(mCheckConnection.check()){
            mViewModel.getSummaryInfoLessons()
        }
        else{
            mViewModel.getLessonsCache()
            context?.let { showToast(it, resources.getString(R.string.no_connection)) }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}