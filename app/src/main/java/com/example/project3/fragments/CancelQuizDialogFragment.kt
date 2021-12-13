package com.example.project3.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.project3.QuizActivity
import com.example.project3.R
import com.example.project3.data.AppDatabase
import java.lang.IllegalStateException

class CancelQuizDialogFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.dialog_cancel_quiz)
                .setPositiveButton(R.string.dialog_yes
                ) { _, _ ->
                    (it as QuizActivity).cancelQuiz()
                }
                .setNegativeButton(R.string.dialog_no) { _, _ -> }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}