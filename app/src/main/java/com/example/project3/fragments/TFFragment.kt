package com.example.project3.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.project3.R
import com.example.project3.data.Question

class TFFragment: QuizFragment(R.layout.quiz_layout_tf) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val question = requireArguments().getParcelable<Question>("question") as Question;

        val questionText = view.findViewById<TextView>(R.id.questionTextView)

        questionText.text = question.question

        val btnTrue = view.findViewById<Button>(R.id.true_button)
        val btnFalse = view.findViewById<Button>(R.id.false_button)

        btnTrue.setOnClickListener {
            this.onSubmitAnswer("True")
        }

        btnFalse.setOnClickListener {
            this.onSubmitAnswer("False")
        }
    }
}