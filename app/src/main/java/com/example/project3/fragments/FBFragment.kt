package com.example.project3.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.project3.R
import com.example.project3.data.Question

class FBFragment: QuizFragment(R.layout.quiz_layout_fb) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val question = requireArguments().getParcelable<Question>("question") as Question;

        val questionTextView = view.findViewById<TextView>(R.id.questionTextView);
        val editTextAnswer = view.findViewById<EditText>(R.id.editTextAnswer)
        val btnSubmit = view.findViewById<Button>(R.id.btnFbSubmit)

        questionTextView.text = question.question

        btnSubmit.setOnClickListener {
            if (editTextAnswer.text.trim().isNotEmpty()) {
                this.onSubmitAnswer(editTextAnswer.text.trim().toString().lowercase())
            }
        }
    }
}