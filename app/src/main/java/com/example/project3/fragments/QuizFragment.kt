package com.example.project3.fragments

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.project3.QuizActivity

abstract class QuizFragment(layoutId: Int): Fragment(layoutId) {

    fun onSubmitAnswer(answer: String) {
        val quizActivity = requireActivity() as QuizActivity

        quizActivity.submitQuestionAnswer(answer)
    }
}