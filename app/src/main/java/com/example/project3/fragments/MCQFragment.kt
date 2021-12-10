package com.example.project3.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.project3.R
import com.example.project3.data.Question

class MCQFragment: QuizFragment(R.layout.quiz_layout_mcq) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val question = requireArguments().getParcelable<Question>("question") as Question;

        val questionText = view.findViewById<TextView>(R.id.questionTextView)
        questionText.text = question.question

        val choices = question.possibleAnswers

        if (choices.size == 3)
            Log.e("QUESTION", "Question with 3 choices: " + question.questionId)

        val btnA = view.findViewById<Button>(R.id.btnA)
        val btnB = view.findViewById<Button>(R.id.btnB)
        val btnC = view.findViewById<Button>(R.id.btnC)
        val btnD = view.findViewById<Button>(R.id.btnD)

        btnA.text = choices[0]
        btnB.text = choices[1]
        btnC.text = choices[2]
        btnD.text = choices[3]

        btnA.setOnClickListener {
            onSubmitAnswer(choices[0])
        }

        btnB.setOnClickListener {
            onSubmitAnswer(choices[1])
        }

        btnC.setOnClickListener {
            onSubmitAnswer(choices[2])
        }

        btnD.setOnClickListener {
            onSubmitAnswer(choices[3])
        }

    }

}